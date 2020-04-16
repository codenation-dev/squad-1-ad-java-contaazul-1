<template>
  <div class="centralizado">
    <h1>{{titulo}}</h1>
    <div>
      <label>{{senhaAtual}}</label>
      <input type="text" />
    </div>
    <div class="margem">
      <label>{{novaSenha}}</label>
      <input type="text" />
    </div>
    <div class="margem">
      <label>{{confirmacao}}</label>
      <input type="text" />
    </div>
    <div>
      <button class="ok">Ok</button>
      <button class="cancelar">Cancelar</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      titulo: "Trocar senha",
      senhaAtual: "Senha Atual:",
      novaSenha: "Nova senha:",
      confirmacao: "Confirmação:"
    };
  }
};
</script>
<style>
.centralizado {
  text-align: center;
}
input {
  font-size: 15px;
  padding: 8px 15px;
  text-align: left;
  color: black;
  width: 50%;
}
button {
  border-radius: 4px;
  font-size: 15px;
  padding: 10px 20px;
  text-align: center;
  color: white;
  margin-top: 10px;
}

label {
  font-size: 20px;
}
.ok {
  background-color: #4caf50;
}
.cancelar {
  background-color: #f44336;
}
.margem {
  margin-top: 10px;
}
</style>