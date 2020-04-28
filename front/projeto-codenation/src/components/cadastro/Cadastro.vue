<template>
  <div id="cadastro" class="centralizado">
    <h1>{{ titulo }}</h1>
    <div>
      <input type="text" placeholder="e-mail" />
    </div>
    <div class="margem">
      <input type="password" placeholder="Senha" />
    </div>
    <div class="margem">
      <input type="password" placeholder="Confirmar senha" />
    </div>
    <div>
      <router-link to="/home">
        <button>{{ cadastrar }}</button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "cadastro",
  data() {
    return {
      titulo: "Cadastrar-se",
      cadastrar: "Cadastrar"
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
.margem {
  margin-top: 10px;
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
</style>
