// Global variable to store the current angle
let angle = 0;

// Function to fetch dinosaurs
async function fetchDinosaurs() {
  try {
    const response = await fetch('/api/v1/dinosaurs');
    if (response.ok) {
      const data = await response.json();
      const dinosaurListElement = document.getElementById('dinosaur-list');
      const cardCount = data.length;
      const angleStep = 360 / cardCount;

      data.forEach((dino, index) => {
        const card = document.createElement('div');
        card.classList.add('dino-card');
        card.innerHTML = `
          <h3>${dino.name}</h3>
          <p>Price: ${dino.price}</p>
        `;
        card.style.transform = `rotateY(${angleStep * index}deg) translateZ(600px)`;
        dinosaurListElement.appendChild(card);
      });

    } else {
      console.error('Failed to fetch dinosaur data');
    }
  } catch (error) {
    console.error('An error occurred:', error);
  }
}

// Function to rotate the cards
function rotateCarousel(direction) {
  const step = 30;
  angle = (direction === 'left') ? angle + step : angle - step;
  const cards = document.querySelectorAll('.dino-card');
  cards.forEach((card) => {
    const currentAngle = parseInt(card.style.transform.match(/rotateY\(([-0-9]+)deg\)/)[1], 10);
    card.style.transform = `rotateY(${currentAngle + step}deg) translateZ(600px)`;
  });
}

// Function to show modal
function showModal() {
  const modal = document.getElementById('orderPreviewModal');
  modal.style.display = 'block';
}

// Event listener to fetch dinosaurs on page load
window.addEventListener('load', fetchDinosaurs);

// Event listener to attach rotate function and showModal
document.addEventListener('DOMContentLoaded', () => {
  const leftArrow = document.getElementById('left-arrow');
  const rightArrow = document.getElementById('right-arrow');
  const continueButton = document.getElementById('continue-button');

  if (leftArrow && rightArrow) {
    leftArrow.addEventListener('click', () => rotateCarousel('left'));
    rightArrow.addEventListener('click', () => rotateCarousel('right'));
  }

  if (continueButton) {
    continueButton.addEventListener('click', showModal);
  }
});
