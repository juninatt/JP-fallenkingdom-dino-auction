// Global variable to store the current angle
let angle = 0;

// Event listener to fetch dinosaurs on page load
window.addEventListener('load', async () => {
  const data = await fetchDinosaurs();
  if (data) {
    renderDinosaurs(data);
  }
});

// Event listener to attach rotate function and showModal
document.addEventListener('DOMContentLoaded', () => {
  const leftArrow = document.getElementById('left-arrow');
  const rightArrow = document.getElementById('right-arrow');

  if (leftArrow && rightArrow) {
    leftArrow.addEventListener('click', () => rotateCarousel('left'));
    rightArrow.addEventListener('click', () => rotateCarousel('right'));
  }
});

// Function to rotate the cards
function rotateCarousel(direction) {
  const cards = document.querySelectorAll('.dino-card');
  cards.forEach((card) => {
    if (card.style && card.style.transform) {
      const matched = card.style.transform.match(/rotateY\s?\(\s?([-0-9.]+)\s?deg\s?\)/);
      if (matched && matched[1]) {
        let currentAngle = parseInt(matched[1], 10);
        currentAngle = (direction === 'left') ? currentAngle + angleStep : currentAngle - angleStep;

        card.style.transform = `rotateY(${currentAngle}deg) translateZ(1000px)`;
      } else {
        console.error("Failed to match rotation angle");
      }
    } else {
      console.error("card.style or card.style.transform does not exist");
    }
  });
}
