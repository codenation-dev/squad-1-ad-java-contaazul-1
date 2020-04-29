<template>
  <div id="cadastro" class="centralizado">
    <h1>{{ titulo }}</h1>
    <form @submit.prevent="salvar">
      <div class="margem">
        <input
          name="nome"
          data-vv-as="nome"
          v-validate
          data-vv-rules="required"
          type="text"
          size="50"
          placeholder="nome"
          v-model="usuario.nome"
          autocomplete="off"
        />
        <div>
          <span class="erro" v-show="errors.has('nome')">{{ errors.first('nome') }}</span>
        </div>
      </div>
      <div class="margem">
        <input
          name="email"
          data-vv-as="e-mail"
          v-validate
          data-vv-rules="required"
          type="text"
          size="50"
          placeholder="e-mail"
          v-model="usuario.email"
          autocomplete="off"
        />
        <div>
          <span class="erro" v-show="errors.has('email')">{{ errors.first('email') }}</span>
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
          v-model="usuario.senha"
          autocomplete="off"
        />
        <div>
          <span class="erro" v-show="errors.has('senha')">{{ errors.first('senha') }}</span>
        </div>
      </div>
      <div class="margem">
        <button>Cadastrar</button>
      </div>
    </form>
  </div>
</template>

<script>
import Usuario from "../../services/usuarios";

export default {
  name: "cadastro",
  data() {
    return {
      titulo: "Cadastro",
      usuario: {
        nome: null,
        email: null,
        senha: null
      }
    };
  },
  methods: {
    salvar() {
      this.$validator.validateAll().then(success => {
        if (success) {
          Usuario.salvar(this.usuario).then(resposta => {
            console.log("Salvo com sucesso");
          });
        } else {
          console.log("Não foi possivel salvar usuario");
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

.margem {
  margin-top: 10px;
}
.inline {
  margin-top: 50px;
  display: inline;
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
.erro {
  color: red;
}
</style>
