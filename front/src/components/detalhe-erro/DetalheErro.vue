<template>
  <div>
    <div style="height: 140px" class="text-center">
      <img class="logo" src="src\components\imagens\logo-central.png " style="margin: 0 0 15px; " />
    </div>
    <br>
    <br>
    <div class="titulo">
      <div>
        <label>Detalhes do erro</label>
      </div>
    </div>
    <div class="jumbotron pt-2">
      <div class="erro">
        <label>Erro no {{erros.origem}} em {{erros.dataErro}}</label>
      </div>
      <div>
        <div class="titulos-detalhes">
          <div>
            <label>Título:</label>
          </div>
        </div>
        <div class="detalhes">
          <div>
            <label>{{erros.titulo}}</label>
          </div>
        </div>
         <div class="titulos-detalhes">
          <div>
            <label>Detalhes:</label>
          </div>
        </div>
        <div class="detalhes">
          <div>
            <label>{{erros.detalhes}}</label>
          </div>
        </div>
        <div class="titulos-detalhes">
          <div>
            <label>Ambiente:</label>
          </div>
        </div>
        <div class="detalhes">
          <div>
            <label>{{erros.ambiente}}</label>
          </div>
        </div>
        <div class="titulos-detalhes">
          <div>
            <label>Arquivado:</label>
          </div>
        </div>
        <div class="detalhes">
          <div>
            <label>{{erros.arquivado}}</label>
          </div>
        </div>
        <div class="titulos-detalhes">
          <div>
            <label>Coletado por:</label>
          </div>
        </div>
        <div class="token">
          <div>
            <label>{{usuario.token}}</label>
          </div>
        </div>
      </div>
      <br>
      <div class = "voltar">
        <div>
          <button class="btn btn-info" @click="voltar">Voltar</button>
        </div>
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
.titulo {
  text-align: center;
  font-size: 30px;
  margin-top: 10px;
  font-weight: bold;
}
.erro {
  font-size: 30px;
  margin-top: 10px;
}
.token {
  font-size: 13px;
  margin-top: 0px;
  word-break: break-all;
}
.titulos-detalhes {
  font-size: 25px;
  margin-top: 10px;
  font-weight: bold;
}
.detalhes {
  font-size: 20px;
  margin-top: 0px;
}
.margin {
  margin-top: 20px;
}
.direita {
  float: right;
}
.voltar {
    margin-left: auto;
    margin-right: auto;
    width: 8em;
}
.jumbotron
{   
    background: #f7f7f7; 
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    margin-left: auto;
    margin-right: auto;
    width: 8em;
    padding-top: 0px;
    padding-bottom:0px;
    background-size: cover;
    background: contain;
    width: 40%; /* make sure to define width to fill container */
    height: 640px; /* define the height in pixels or make sure   */
                   /* you have something in your div with height */
                   /* so you can see your image */
    max-width:1400px;  /* define the max width */
 }
</style>