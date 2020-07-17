function Game() {
  const $showTargetButton = document.querySelector("#show-target");
  const $guessButton = document.querySelector("#guess-button");
  const $guessInput = document.querySelector("#guess-input");
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

  const onGuessHandler = async (event) => {
    event.preventDefault();
    const gameId = location.split("/")[2];
    const guessValue = $guessInput.value;
    const request = {"guess": guessValue};
    const response = await fetch(`/guesses/${gameId}`, {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(request)
    });
    const responseObject = await response.json();
    if (responseObject.guessResultType === "MATCH") {
      alert("정답!");
    } else if (responseObject.ballCount === 0 && responseObject.strikeCount === 0) {
      alert("낫싱");
    } else {
      alert(responseObject.strikeCount + " 스트라이크 " + responseObject.ballCount + " 볼");
    }

    console.log(responseObject);
  }

  const initEventListener = () => {
    $showTargetButton.addEventListener("click", onShowTargetHandler);
    $guessButton.addEventListener("click", onGuessHandler)
  }

  this.init = () => {
    initGame();
    initEventListener();
  };
}

const game = new Game();
game.init();