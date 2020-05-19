<template>
  <div>
    <div class="container">
      <div style="width: 340px; margin: 50px auto;">
        <div
          style="margin-bottom: 15px; background: #f7f7f7; box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3); padding: 30px;"
        >
          <h2 class="text-center" style="margin: 0 0 15px;">{{ titulo }}</h2>
          <div class="form-group">
            <input class="form-control" name="email" placeholder="E-mail" autocomplete="off" />
          </div>
          <div class="form-group">
            <input
              class="form-control"
              name="senhaAntiga"
              placeholder="Senha Antiga"
              autocomplete="off"
            />
          </div>
          <div class="form-group">
            <input
              class="form-control"
              name="senhaNova"
              placeholder="Senha nova"
              autocomplete="off"
            />
          </div>
          <div class="centralizado">
            <button class="btn btn-success">Confirmar</button>
            <button class="btn btn-danger">Cancelar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      titulo: "Alterar senha"
    };
  }
};
</script>
<style>
.centralizado {
  text-align: center;
}
</style>