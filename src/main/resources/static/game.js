function Game() {
  this.init = () => {
    fetch("/baseballs", {
      method: "POST"
    }).then(response => {
      console.log(response.headers.get("location"))
    });
  };
}

const game = new Game();
game.init();