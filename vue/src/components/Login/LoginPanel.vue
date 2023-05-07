<template>
  <div class="dd">
    <div class="container" :class="{'right-panel-active': isRightPanelActive}">
      <div class="form-container sign-up-container">
        <div class="form">
          <h1>Stwórz konto</h1>
          <span>lub przejdź dorejestracji</span>
          <input type="text" placeholder="Username" />
          <input type="password" placeholder="Hasło" />
          <input type="text" placeholder="Aktualny rok studiów" />
          <input type="text" placeholder="Kierunek studiów" />
          <button>Zarejestruj się</button>
        </div>
      </div>
      <div class="form-container sign-in-container">
        <div class="form">
          <h1>Zaloguj się</h1>
          <span>lub utwórz konto</span>
          <input type="text" placeholder="Username" />
          <input type="password" placeholder="Hasło" />
          <a href="#">Nie pamietasz hasła?</a>
          <button>Zaloguj sie</button>
        </div>
      </div>
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <h1>Witaj z powrotem!</h1>
            <button @click="togglePanel" class="ghost">Zaloguj się</button>
          </div>
          <div class="overlay-panel overlay-right">
            <p>Wprowadź swoje dane, aby być na bieżąco z postępem swoich studiów.</p>
            <button @click="togglePanel" class="ghost">Zarejestruj się</button>
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
    isRightPanelActive: false
  }
},

methods: {
  togglePanel() {
    this.isRightPanelActive = !this.isRightPanelActive;
  }
}
}

</script>

<style scoped>
.dd {
  display: flex;
  justify-content: center;
  align-items: center;
}

* {
  box-sizing: border-box;
}

h1 {
  font-weight: bold;
  margin: 0;
}

p {
  font-size: 14px;
  margin: 20px 0 30px;
}

span {
  font-size: 12px;
}

a {
  color: #333;
  font-size: 14px;
  text-decoration: none;
  margin: 15px 0;
}

button {
  border-radius: 20px;
  border: 1px solid #FF4B2B;
  background-color: #FF4B2B;
  font-size: 12px;
  font-weight: bold;
  padding: 12px 45px;
  color: white;
  transition: transform 80ms ease-in;
  margin-top: 30px;
}

button:active {
  transform: scale(0.95);
}

button:focus {
  outline: none;
}

button.ghost {
  background-color: transparent;
  border-color: #FFFFFF;
  margin-top: 35px;
}

.form {
  background-color: #FFFFFF;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 50px;
  height: 100%;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 8px 0;
  width: 100%;
}

.container {
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25),
    0 10px 10px rgba(0, 0, 0, 0.22);
  position: relative;
  overflow: hidden;
  width: 1000px;
  min-height: 600px;
}

.form-container {
  position: absolute;
  top: 0;
  height: 100%;
  transition: all 0.6s ease-in-out;
}

.sign-in-container {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .sign-in-container {
  transform: translateX(100%);
}

.sign-up-container {
  left: 0;
  width: 50%;
  opacity: 0;
  z-index: 1;
}

.container.right-panel-active .sign-up-container {
  transform: translateX(100%);
  opacity: 1;
  z-index: 5;
  animation: show 0.6s;
}

@keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}

.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay-container {
  transform: translateX(-100%);
}

.overlay {
  background: linear-gradient(to right, #124a99, #FF416C);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 0 0;
  color: white;
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 40px;
  height: 100%;
  width: 50%;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  text-align: center;
}

.overlay-left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
  transform: translateX(0);
}
.overlay-right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay-right {
  transform: translateX(20%);
}

.social-container {
  margin: 20px 0;
}
.social-container a {
  border: 1px solid #DDDDDD;
  border-radius: 50%;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  margin: 0 5px;
  height: 40px;
  width: 40px;
}
</style>