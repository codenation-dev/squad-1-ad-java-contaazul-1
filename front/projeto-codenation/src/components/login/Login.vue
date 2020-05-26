<template>
  <div id="app">
    <div class="container">
      <div style="width: 340px; margin: 50px auto;">
        <div
          style="margin-bottom: 15px; background: #f7f7f7; box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3); padding: 30px;"
        >
          <h2 class="text-center" style="margin: 0 0 15px;">{{ titulo }}</h2>
          <div class="form-group">
            <input
              class="form-control"
              name="nome"
              data-vv-as="usuário"
              v-validate
              data-vv-rules="required"
              type="text"
              placeholder="Usuário"
              v-model="nome"
              autocomplete="off"
            />
            <div>
              <span class="erro" v-show="errors.has('nome')">{{ errors.first('nome') }}</span>
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
              v-model="senha"
              autocomplete="off"
            />
            <div>
              <span class="erro" v-show="errors.has('senha')">{{ errors.first('senha') }}</span>
            </div>
          </div>
          <div>
            <button @click="entrar" class="btn btn-primary btn-block">Entrar</button>
          </div>
          <br />
          <div class="clearfix text-center">
            <router-link to="/alterar-senha">Esqueci minha senha</router-link>
          </div>
        </div>
        <p class="text-center">
          <router-link to="/cadastro">Criar conta</router-link>
        </p>
      </div>
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
              console.log(resposta);

              if (resposta.data.accessToken != null) {
                this.$router.push({
                  name: "home",
                  params: {
                    usuario: {
                      nome: this.nome,
                      token: resposta.data.accessToken
                    }
                  }
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