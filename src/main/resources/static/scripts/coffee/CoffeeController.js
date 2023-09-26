import CoffeeModel from './CoffeeModel.js';
import CoffeeView from './CoffeeView.js';

export default class CoffeeController {
  constructor() {
    this.model = new CoffeeModel();
    this.view = new CoffeeView();
    this.init();
  }

  async init() {
    await this.populateInitialCoffeeList();
    document.getElementById('continue-button').addEventListener('click', () => this.onContinueClick());
    document.getElementById('confirmOrderBtn').addEventListener('click', () => this.onConfirmClick());
    document.getElementById('closeModalBtn').addEventListener('click', function() {
      document.getElementById('orderPreviewModal').style.display = 'none';
    });
  }

  async populateInitialCoffeeList() {
    const response = await fetch('/api/v1/coffee');
    const coffees = await response.json();
    this.model.updateCoffees(coffees);
    this.view.updateCoffeeList(this.model.coffees, this.model.selectedQuantities);
  }

  async onContinueClick() {
    try {
      // Sending the selected quantities to the server to get the order summary
      const response = await fetch('/api/v1/orders/summary', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.model.selectedQuantities)
      });

      const data = await response.json();

      this.model.updateOrderPreview(data);
      this.view.updateOrderPreview(this.model.orderPreview);
    } catch (error) {
      console.log("Error:", error);
    }
  }

  async onConfirmClick() {
    try {
      // Sending the confirmed order data to the server for further processing
      const response = await fetch('/coffee/something', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.model.orderPreview)
      });

      const data = await response.json();
      if (data.success) {
          console.log('Order confirmed:', data);
          // Close the modal or navigate to another page, etc.
          document.getElementById('orderPreviewModal').style.display = 'none';
      } else {
          console.log('Order could not be confirmed:', data);
      }
    } catch (error) {
      console.log("Error:", error);
    }
  }

}
