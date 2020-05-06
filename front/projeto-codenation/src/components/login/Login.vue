<template>
  <div id="app" class="centralizado">
    <h1>{{ titulo }}</h1>
    <div class="margem">
      <input
        name="nome"
        data-vv-as="usuário"
        v-validate
        data-vv-rules="required"
        type="text"
        size="50"
        placeholder="Usuário"
        v-model="nome"
        autocomplete="off"
      />
      <div>
        <span class="erro" v-show="errors.has('nome')">{{ errors.first('nome') }}</span>
      </div>
    </div>
    <div class="margem">
      <input
        name="senha"
        data-vv-as="senha"
        v-validate
        data-vv-rules="required"
        type="password"
        size="50"
        placeholder="senha"
        v-model="senha"
        autocomplete="off"
      />
      <div>
        <span class="erro" v-show="errors.has('senha')">{{ errors.first('senha') }}</span>
      </div>
    </div>
    <div>
      <button @click="entrar">Entrar</button>
    </div>
    <div class="margem">
      <router-link to="/alterar-senha">Esqueci minha senha</router-link>
      <router-link to="/cadastro">Criar nova conta</router-link>
    </div>
  </div>
</template>

<script>
import Usuario from "../../services/usuarios";

export default {
  name: "app",
  data() {
    return {
      titulo: "Login",
      nome: null,
      senha: null
    };
  },
  methods: {
    entrar() {
      this.$validator.validateAll().then(success => {
        if (success) {
          Usuario.login(this.nome, this.senha).then(resposta => {
            if (resposta) {
              if (resposta.data.id != null) {
                this.$router.push({
                  name: "home",
                  params: { nome: resposta.data.nome, token: "4d5as4das5d4sa" }
                });
              } else {
                alert("Usuário não registrado");
              }
            } else {
              console.log("Não conseguiu conexão com servidor");
            }
          });
        }
      });
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
.erro {
  color: red;
}
</style>
