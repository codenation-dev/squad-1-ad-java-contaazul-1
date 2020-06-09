<template>
  <div id="cadastro" class="centralizado">
    <div class="container">
      <div style="width: 340px; margin: 50px auto;">
        <div
          style="margin-bottom: 15px; background: #f7f7f7; box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3); padding: 30px;"
        >
          <div>
            <img
              class="logoLogin"
              src="src\components\imagens\logo-central.png "
              style="margin: 0 0 30px; "
              width="275"
              height="80"
            />
          </div>
          <h2 class="text-center" style="margin: 0 0 15px;">{{ titulo }}</h2>
          <form @submit.prevent="salvar">
            <div class="form-group">
              <input
                class="form-control"
                name="nome"
                data-vv-as="nome"
                v-validate
                data-vv-rules="required"
                type="text"
                size="50"
                placeholder="Nome"
                v-model="usuario.nome"
                autocomplete="off"
              />
              <div>
                <span class="erro" v-show="errors.has('nome')">{{ errors.first('nome') }}</span>
              </div>
            </div>
            <div class="form-group">
              <input
                class="form-control"
                name="username"
                data-vv-as="username"
                v-validate
                data-vv-rules="required"
                type="text"
                size="50"
                placeholder="Nome de usuário"
                v-model="usuario.username"
                autocomplete="off"
              />
              <div>
                <span class="erro" v-show="errors.has('username')">{{ errors.first('username') }}</span>
              </div>
            </div>
            <div class="form-group">
              <input
                class="form-control"
                name="email"
                data-vv-as="e-mail"
                v-validate
                data-vv-rules="required"
                type="email"
                size="50"
                placeholder="E-mail"
                v-model="usuario.email"
                autocomplete="off"
              />
              <div>
                <span class="erro" v-show="errors.has('email')">{{ errors.first('email') }}</span>
              </div>
            </div>
            <div class="form-group">
              <input
                class="form-control"
                name="senha"
                data-vv-as="senha"
                v-validate
                data-vv-rules="required"
                type="password"
                size="50"
                placeholder="Senha"
                v-model="usuario.senha"
                autocomplete="off"
              />
              <div>
                <span class="erro" v-show="errors.has('senha')">{{ errors.first('senha') }}</span>
              </div>
            </div>
            <div class="margem">
              <button class="btn btn-primary btn-block">Cadastrar</button>
            </div>
          </form>
        </div>
        <p class="text-center">
          <router-link to="/">Já tenho conta</router-link>
        </p>
      </div>
    </div>
  </div>
</template>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
import Usuario from "../../services/usuarios";

export default {
  name: "cadastro",
  data() {
    return {
      titulo: "Cadastro",
      usuario: {
        nome: null,
        username: null,
        email: null,
        senha: null
      }
    };
  },
  methods: {
    salvar() {
      if (!this.validEmail(this.usuario.email)) {
        alert("Informe um email valido");
        return;
      }
      this.$validator.validateAll().then(success => {
        if (success) {
          Usuario.salvar(this.usuario).then(resposta => {
            swal(
              "Usuário criado com sucesso!",
              "Clique no botão para logar.",
              "success"
            );
            this.$router.push({
              name: "login"
            });
          });
        } else {
          swal(
            "Não foi possível criar usúario!",
            "Tente mais tarde.",
            "warning"
          );
        }
      });
    },
    login() {
      this.$router.push({
        name: "login"
      });
    },
    validEmail(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }
  }
};
</script>

<style>
.erro {
  color: red;
}
</style>
