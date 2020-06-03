function Game() {
  const $showTargetButton = document.querySelector("#show-target");
  let location;

  const initGame = () => {
    fetch("/baseballs", {
      method: "POST"
    }).then(response => {
      location = response.headers.get("location");
    });
  }

  const onShowTargetHandler = async (event) => {
    event.preventDefault();
    const gameId = location.split("/")[2];
    const response = await fetch(`/baseballs/${gameId}/target`, { method: "GET" });
    const responseObject = await response.json();
    const target = responseObject.target;
    alert(target);
  }

  const initEventListener = () => {
    $showTargetButton.addEventListener("click", onShowTargetHandler)
  }

  this.init = () => {
    initGame();
    initEventListener();
  };
}

const game = new Game();
game.init();