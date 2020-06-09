<template>
  <div>
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
          <h3 class="text-center" style="margin: 0 0 15px;">{{ titulo }}</h3>
          <h6 class="text-center" style="margin: 0 0 15px;">{{ subtitulo }}</h6>
          <div class="form-group">
            <input
              class="form-control"
              name="email"
              v-model="usuario.email"
              data-vv-as="e-mail"
              v-validate
              data-vv-rules="required"
              type="email"
              size="50"
              placeholder="E-mail"
              autocomplete="off"
            />
            <div>
              <span class="erro" v-show="errors.has('email')">{{ errors.first('email') }}</span>
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
      titulo: "Esqueci minha senha",
      subtitulo: "Insira seu e-mail e enviaremos um link para alteração da senha",
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
        if(success){
          if (!this.validEmail(this.usuario.email)) {
            alert("Informe um email valido");
            return;
          }
          swal(
            "Verifique sua caixa de entrada",
            "Um link para alteração da senha foi enviado ao e-mail informado" 
          );
          this.$router.push({
            name: "login"
          });
        }
      });
    },
    validEmail(email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }
  }
};
</script>