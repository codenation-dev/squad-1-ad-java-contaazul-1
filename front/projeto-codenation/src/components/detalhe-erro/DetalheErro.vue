<template>
  <div>
    <div class="justify-content-between row col-md-12">
      <div>
        <div>
          <label class="mr-4">Bem vindo(a) {{usuario.nome}}.</label>
          <button @click="logout" class="botaoLogout">
            <i class="gg-log-out corLogout"></i>
          </button>
        </div>
        <label>
          Seu token é
          <small>{{usuario.token}}</small>
        </label>
      </div>
    </div>
    <div class="jumbotron">
      <div class="erro">
        <label>{{erros.dataErro}}</label>
      </div>
      <div>
        <div class="margin">
          <div>
            <label>Título:</label>
          </div>
          <label>{{erros.titulo}}</label>
        </div>
        <div class="margin">
          <div>
            <label>Origem erro:</label>
          </div>
          <label>{{erros.origem}}</label>
        </div>
      </div>
      <div>
        <div class="margin">
          <div>
            <label>Ambiente:</label>
          </div>
          <label>{{erros.ambiente}}</label>
        </div>
        <div class="margin">
          <div>
            <label>Arquivado:</label>
          </div>
          <label>{{erros.arquivado}}</label>
        </div>
        <div class="margin">
          <div>
            <label>Detalhes:</label>
          </div>
          <label>{{erros.detalhes}}</label>
        </div>
        <div class="margin">
          <div>
            <label>Coletado por:</label>
          </div>
          <label>{{usuario.token}}</label>
        </div>
      </div>
      <div>
        <button class="btn btn-info" @click="voltar">Voltar</button>
      </div>
    </div>
  </div>
</template>
<script>
import Erro from "../../services/erros";
export default {
  data() {
    return {
      idErro: null,
      erro: {},
      usuario: {
        nome: null,
        token: null
      },
      erros: {
        ambiente: null,
        detalhes: null,
        titulo: null,
        dataErro: null,
        origem: null,
        arquivado: null
      }
    };
  },
  methods: {
    logout() {
      this.$router.push({
        name: "login"
      });
    },
    voltar() {
      this.$router.push({
        name: "home",
        params: {
          usuario: {
            nome: this.$route.params.usuario.nome,
            token: this.$route.params.usuario.token
          }
        }
      });
    }
  },
  mounted() {
    this.idErro = this.$route.params.id;
    this.usuario = this.$route.params.usuario;

    Erro.detalhesErro(this.idErro, this.usuario.token).then(resposta => {
      this.erros.ambiente = resposta.data.ambiente;
      this.erros.detalhes = resposta.data.detalhes;
      this.erros.titulo = resposta.data.titulo;
      this.erros.dataErro = resposta.data.updatedAt;
      this.erros.origem = resposta.data.origem;
      this.erros.arquivado = resposta.data.arquivado;
    });
  }
};
</script>

<style>
.erro {
  font-size: 30px;
  margin-top: 10px;
}
.margin {
  margin-top: 20px;
}
.direita {
  float: right;
}
</style>