<template>
  <div id="cadastro" class="centralizado">
    <h1>{{ titulo }}</h1>
    <div>
      <input type="text" placeholder="e-mail" />
    </div>
    <div class="margem">
      <input type="password" placeholder="password" />
    </div>
    <div>
      <button>{{cadastrar}}</button>
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
</style>
