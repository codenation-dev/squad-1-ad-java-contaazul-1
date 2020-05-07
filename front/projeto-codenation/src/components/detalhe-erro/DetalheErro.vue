<template>
  <div>
    <div>
      <h1>Bem vindo {{usuario.nome}}. Seu token é {{usuario.token}}</h1>
      <button @click="logout">Sair</button>
    </div>
    <div>
      <button>Voltar</button>
    </div>
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      usuario: {
        nome: "Larissa",
        token: "4d5748sdasd"
      }
    };
  },
  methods: {
    logout() {
      this.$router.push({
        name: "login"
      });
    }
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