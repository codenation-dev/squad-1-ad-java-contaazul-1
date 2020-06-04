<template>
  <div>
    <div style="height: 140px" class="text-center">
      <img class="logo" src="src\components\imagens\logo-central.png " style="margin: 0 0 15px; " />
    </div>
    <div class="justify-content-between justify-content-lg-between row col-md-12 p-0 m-0">
      <div style="width: 90%">
        <label style="font-weight: 700">Bem vindo(a)</label>
        <label style="font-weight: 400; font-style: italic">{{usuario.nome}}.</label>
        <p>
          <label style="font-weight: 700">
            Seu token é
            <label style="font-size: 11px">{{usuario.token}}</label>
          </label>
        </p>
      </div>
      <div class="align-content-around row pr-3">
        <button @click="logout" class="botaoLogout">
          <i class="gg-log-out corLogout"></i>
        </button>
      </div>
    </div>
    <div class="jumbotron pt-2">
      <div class="erro">
        <label>Data do erro: {{erros.dataErro}}</label>
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
    },
    obterDataFormatadaPT(data) {
      var pular = data.split("-");
      var dia = pular[2].substring(0, 2);
      var mes = pular[1];
      var ano = pular[0];
      var dataFormatada = new Date(ano, mes - 1, dia).toLocaleDateString(
        "pt-BR",
        {
          year: "numeric",
          month: "2-digit",
          day: "2-digit"
        }
      );
      return dataFormatada;
    }
  },
  mounted() {
    this.idErro = this.$route.params.id;
    this.usuario = this.$route.params.usuario;

    Erro.detalhesErro(this.idErro, this.usuario.token).then(resposta => {
      this.erros.ambiente = resposta.data.ambiente;
      this.erros.detalhes = resposta.data.detalhes;
      this.erros.titulo = resposta.data.titulo;
      this.erros.dataErro = this.obterDataFormatadaPT(resposta.data.updatedAt);
      this.erros.origem = resposta.data.origem;
      this.erros.arquivado = resposta.data.arquivado ? "Sim" : "Não";
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