export default class CoffeeView {
  updateCoffeeList(coffees, selectedQuantities) {
    const coffeeContainer = document.getElementById('coffee-list');
    coffeeContainer.innerHTML = '';
    coffees.forEach(coffee => {
      const coffeeCard = this.createCoffeeCard(coffee, selectedQuantities);
      coffeeContainer.appendChild(coffeeCard);
    });
  }

  createCoffeeCard(coffee, selectedQuantities) {
    const coffeeCard = document.createElement('div');
    coffeeCard.classList.add('coffee-card');

    const essentialDetails = this.createEssentialDetails(coffee);
    const extendedDetails = this.createExtendedDetails(coffee);
    const toggleButton = this.createToggleButton();

    toggleButton.onclick = () => extendedDetails.classList.toggle('visible');

    essentialDetails.querySelector('.quantity-selector').addEventListener('change', (e) => {
      selectedQuantities[coffee.id] = e.target.value;
    });

    coffeeCard.append(essentialDetails, toggleButton, extendedDetails);
    return coffeeCard;
  }

  createEssentialDetails(coffee) {
    const essentialDetails = document.createElement('div');
    essentialDetails.innerHTML = `
        <div class="details-row">
            <span class="details-item"><span class="details-label">Name:</span> <span class="detail-value">${coffee.name}</span></span>
            <span class="details-item"><span class="details-label">Origin:</span> <span class="detail-value">${coffee.origin}</span></span>
            <span class="details-item"><span class="details-label">Price:</span> <span class="detail-value">$${coffee.price}</span></span>
        </div>
        <div class="quantity-selector-row">
            <input type="number" class="quantity-selector" min="1" max="10" value="0" data-coffee-id="${coffee.id}">
        </div>
    `;
    return essentialDetails;
  }

  createExtendedDetails(coffee) {
    const extendedDetails = document.createElement('div');
    extendedDetails.classList.add('extended-details');
    extendedDetails.innerHTML = `
        <p>Roast Level: ${coffee.roastLevel}</p>
        <p>Flavor Notes: ${coffee.flavorNotes}</p>
        <p>Caffeine Content: ${coffee.caffeineContent}</p>
    `;
    return extendedDetails;
  }

  createToggleButton() {
    const toggleButton = document.createElement('button');
    toggleButton.innerText = "More Info";
    toggleButton.className = "coffee-action-button";
    return toggleButton;
  }

  updateOrderPreview(orderPreviewDTO) {
    const orderList = document.getElementById('orderPreviewList');
    orderList.innerHTML = '';

    for (let coffeeName of Object.keys(orderPreviewDTO.products)) {
      const quantity = orderPreviewDTO.products[coffeeName];
      const li = document.createElement('li');
      li.textContent = `${coffeeName} (x${quantity})`; // assuming the price will be added later
      orderList.appendChild(li);
    }

    document.getElementById('orderTotalPrice').textContent = `$${orderPreviewDTO.totalSum}`;
    document.getElementById('orderPreviewModal').style.display = 'block';
  }
}
