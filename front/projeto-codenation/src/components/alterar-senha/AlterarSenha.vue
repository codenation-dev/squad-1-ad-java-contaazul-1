<template>
  <div>
    <div class="container">
      <div style="width: 340px; margin: 50px auto;">
        <div
          style="margin-bottom: 15px; background: #f7f7f7; box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3); padding: 30px;"
        >
          <h2 class="text-center" style="margin: 0 0 15px;">{{ titulo }}</h2>
          <div class="form-group">
            <input
              class="form-control"
              name="email"
              v-model="usuario.email"
              data-vv-as="e-mail"
              v-validate
              data-vv-rules="required"
              placeholder="E-mail"
              autocomplete="off"
            />
            <div>
              <span class="erro" v-show="errors.has('email')">{{ errors.first('email') }}</span>
            </div>
          </div>
          <div class="form-group">
            <input
              class="form-control"
              name="senhaAntiga"
              v-model="usuario.senhaAntiga"
              data-vv-as="senha antiga"
              v-validate
              data-vv-rules="required"
              type="password"
              placeholder="Senha antiga"
              autocomplete="off"
            />
            <div>
              <span
                class="erro"
                v-show="errors.has('senhaAntiga')"
              >{{ errors.first('senhaAntiga') }}</span>
            </div>
          </div>
          <div class="form-group">
            <input
              class="form-control"
              name="senhaNova1"
              v-model="usuario.senhaNova1"
              data-vv-as="senha nova"
              v-validate
              data-vv-rules="required"
              type="password"
              placeholder="Senha nova"
              autocomplete="off"
            />
            <div>
              <span class="erro" v-show="errors.has('senhaNova1')">{{ errors.first('senhaNova1') }}</span>
            </div>
          </div>
          <div class="form-group">
            <input
              class="form-control"
              name="senhaNova2"
              v-model="usuario.senhaNova2"
              data-vv-as="senha nova"
              v-validate
              data-vv-rules="required"
              type="password"
              placeholder="Confirmar senha nova"
              autocomplete="off"
            />
            <div>
              <span class="erro" v-show="errors.has('senhaNova2')">{{ errors.first('senhaNova2') }}</span>
            </div>
          </div>
          <div class="centralizado">
            <button class="btn btn-success col-sm-5" @click="alterarSenha">Confirmar</button>
            <button class="btn btn-danger col-sm-5" @click="cancelar">Cancelar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Usuario from "../../services/usuarios";

export default {
  data() {
    return {
      titulo: "Alterar senha",
      usuario: {
        email: null,
        senhaAntiga: null,
        senhaNova1: null,
        senhaNova2: null
      }
    };
  },
  methods: {
    cancelar() {
      this.$router.push({
        name: "login"
      });
    },
    alterarSenha() {
      this.$validator.validateAll().then(success => {
        if (success) {
          Usuario.alterarSenha(this.usuario).then(resposta => {
            if (resposta) {
              console.log(resposta);
            }
          });
        }
      });
    }
  }
};
</script>