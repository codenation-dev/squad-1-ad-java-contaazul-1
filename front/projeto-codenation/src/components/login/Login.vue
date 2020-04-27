<template>
  <div id="app" class="centralizado">
    <h1>{{ titulo }}</h1>//teste
    <div class="margem">
      <input type="text" placeholder="e-mail" />
    </div>
    <div class="margem">
      <input type="password" placeholder="password" />
    </div>
    <div>
      <router-link to="/home">
        <button>Entrar</button>
      </router-link>
    </div>
    <div class="margem">
      <router-link to="/alterar-senha">Esqueci minha senha</router-link>
      <router-link to="/cadastro">Criar nova conta</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {
      titulo: "Login"
    };
  },
  methods: {
    cadastrar() {
      alert("entrei em outra tela");
    }
  }
};
</script>

<style>
.centralizado {
  text-align: center;
}
button {
  border-radius: 4px;
  font-size: 15px;
  padding: 10px 20px;
  text-align: center;
  color: white;
  margin-top: 10px;
  background-color: blue;
  width: 15%;
}
input {
  font-size: 15px;
  padding: 8px 10px;
  text-align: left;
  color: black;
  width: 50%;
}
.margem {
  margin-top: 10px;
}
</style>
