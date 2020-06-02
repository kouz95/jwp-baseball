function Index() {
  const $startButton = document.querySelector("#start");

  const onStartHandler = (event) => {
    event.preventDefault();
    location.href = "/game"
  }

  const initEventListener = () => {
    $startButton.addEventListener("click", onStartHandler)
  }

  this.init = () => {
    initEventListener();
  };
}

const index = new Index();
index.init();