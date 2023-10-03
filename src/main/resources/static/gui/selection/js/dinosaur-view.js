let angleStep;

// Function to render dinosaurs in the UI
function renderDinosaurs(data) {
  const dinosaurListElement = document.getElementById('dinosaur-list');
  const cardCount = data.length;
  angleStep = Math.round(360 / cardCount);

  data.forEach((dino, index) => {
    const card = document.createElement('div');
    card.classList.add('dino-card');
    card.style.backgroundImage = `url(${dino.imageResource})`;
    card.innerHTML = `
      <h3>${dino.name}</h3>
      <p>Price: ${dino.priceInDollar}</p>
    `;
    card.style.transform = `rotateY(${angleStep * index}deg) translateZ(1000px)`;
    dinosaurListElement.appendChild(card);

    const clone = card.cloneNode(true);
    clone.classList.add('dino-card-clone');
    dinosaurListElement.appendChild(clone);
  });
}


// Function to show modal
function showModal() {
  const modal = document.getElementById('orderPreviewModal');
  modal.style.display = 'block';
}
