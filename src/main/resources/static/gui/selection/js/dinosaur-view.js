let angleStep;

// Function to render dinosaurs in the UI
function renderDinosaurs(data) {
  console.log("Data inside renderDinosaurs:", data);

  if (!data || !data.dinoCardDataList) {
    console.error("Invalid data passed to renderDinosaurs");
    return;
  }

  const dinoCardDataList = data.dinoCardDataList;
  const dinosaurListElement = document.getElementById('dinosaur-list');
  dinosaurListElement.innerHTML = ''; // Clear previous cards, if any.
  const cardCount = dinoCardDataList.length;
  angleStep = Math.round(360 / cardCount);

  dinoCardDataList.forEach((dino, index) => {
    const card = document.createElement('div');
    card.classList.add('dino-card');
    card.style.backgroundImage = `url(${dino.imageResource})`;
    const descriptionSnippet = dino.description.substring(0, 50) + '...';
    card.innerHTML = `
      <h3>${dino.name}</h3>
      <p>Species: ${dino.species}</p>
      <p>Gender: ${dino.gender}</p>
      <p>Age: ${dino.ageInYears} years</p>
      <p>Weight: ${dino.weightInKg} kg</p>
      <p>Price: ${dino.priceInDollar}</p>
      <p>Description: <span id="desc-${index}" onclick="toggleDescription(${index})">${descriptionSnippet}</span></p>
    `;
    card.style.transform = `rotateY(${angleStep * index}deg) translateZ(1000px)`;
    dinosaurListElement.appendChild(card);

    const clone = card.cloneNode(true);
    clone.classList.add('dino-card-clone');
    dinosaurListElement.appendChild(clone);
  });
}

// Function to toggle the description
function toggleDescription(index) {
  const descSpan = document.getElementById(`desc-${index}`);
  const fullDescription = dinoCardDataList[index].description; // Assuming dinoCardDataList is globally accessible
  if (descSpan.innerText === fullDescription) {
    descSpan.innerText = fullDescription.substring(0, 50) + '...';
  } else {
    descSpan.innerText = fullDescription;
  }
}
