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
        <label>Erro no 127.0.0.1 em 13/04/2020 10:15</label>
      </div>
      <div>
        <div class="margin">
          <div>
            <label>Título</label>
          </div>
          <label>dasdadasdasdadasdadasdasda</label>
        </div>
        <div class="direita">
          <label>Erro</label>
          <label>Eventos</label>
          <label>1000</label>
        </div>
      </div>
      <div>
        <div class="margin">
          <div>
            <label>Detalhes</label>
          </div>
          <label>dasdadasdasdadasdadasdasda</label>
        </div>
        <div class="direita">
          <label>Coletado por</label>
          <label>Aqui passa token</label>
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
        nome: "Atari",
        token: "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdGFyaSIsImlhdCI6MTU4OTk0MDEwMg"
      },
      erros: null
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
        name: "home"
      });
    }
  },
  mounted() {
    this.idErro = this.$route.params.id;
    //this.usuario.nome = this.$route.params.usuario;
    //this.usuario.token = this.$route.params.token;

    console.log(this.idErro);

    Erro.listarErro(this.idErro).then(resErro => {
      let errors = resErro.data;
      //this.erros = errors
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