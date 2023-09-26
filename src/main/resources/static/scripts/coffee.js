// Function to create essential details for each coffee card
 function createEssentialDetails(coffee) {
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

 // Function to create extended details for each coffee card
 function createExtendedDetails(coffee) {
     const extendedDetails = document.createElement('div');
     extendedDetails.classList.add('extended-details');
     extendedDetails.innerHTML = `
         <p>Roast Level: ${coffee.roastLevel}</p>
         <p>Flavor Notes: ${coffee.flavorNotes}</p>
         <p>Caffeine Content: ${coffee.caffeineContent}</p>
     `;
     return extendedDetails;
 }

 // Function to create a toggle button for extended details
 function createToggleButton() {
     const toggleButton = document.createElement('button');
     toggleButton.innerText = "More Info";
     toggleButton.className = "coffee-action-button";
     return toggleButton;
 }

 // Main function to create each coffee card
 function createCoffeeCard(coffee, selectedQuantities) {
     const coffeeCard = document.createElement('div');
     coffeeCard.classList.add('coffee-card');

     const essentialDetails = createEssentialDetails(coffee);
     const extendedDetails = createExtendedDetails(coffee);
     const toggleButton = createToggleButton();

     toggleButton.onclick = () => extendedDetails.classList.toggle('visible');

     essentialDetails.querySelector('.quantity-selector').addEventListener('change', (e) => {
         selectedQuantities[coffee.id] = e.target.value;
     });

     coffeeCard.append(essentialDetails, toggleButton, extendedDetails);
     return coffeeCard;
 }

 // Object to hold selected quantities
 const selectedQuantities = {};

// Fetch coffee data from the server
 fetch('/api/v1/coffee')
     .then(response => response.json())
     .then(data => {
        // Grab the container where coffee cards will be appended
         const coffeeContainer = document.getElementById('coffee-list');
        // Loop through each coffee and create its card for display
         data.forEach(coffee => {
             const coffeeCard = createCoffeeCard(coffee, selectedQuantities);
             coffeeContainer.appendChild(coffeeCard);
         });
     });

// Function to handle 'Continue' button click
async function onContinueClick() {
    try {
       // Sending the selected quantities to the server to get the order summary
        const response = await fetch('/api/v1/orders/summary', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(selectedQuantities)
        });

        const data = await response.json();

        // Updating the modal with order details received from the server
        const orderList = document.getElementById('orderPreviewList');
        orderList.innerHTML = '';

        // Iterate over the 'products' object in the returned data
        for (let coffee of Object.keys(data.products)) {
            const quantity = data.products[coffee];
            const li = document.createElement('li');
            li.textContent = `${coffee.name} (x${quantity}): ${coffee.price}`;
            orderList.appendChild(li);
        }
        document.getElementById('orderTotalPrice').textContent = `$${data.totalSum}`;

        // Showing the modal with the order details
        document.getElementById('orderPreviewModal').style.display = 'block';

    } catch (error) {
        console.log("Error:", error);
    }
}



// Close modal when the 'Close' button is clicked
document.getElementById('closeModalBtn').addEventListener('click', function() {
    document.getElementById('orderPreviewModal').style.display = 'none';
});



// Add click event listener to the 'Continue' button
document.getElementById('continue-button').addEventListener('click', onContinueClick);



