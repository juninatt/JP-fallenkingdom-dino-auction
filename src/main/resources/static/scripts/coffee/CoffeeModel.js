export default class CoffeeModel {
  constructor() {
    this.coffees = [];
    this.selectedQuantities = {};
    this.orderPreview = null;
  }

  updateCoffees(coffees) {
    this.coffees = coffees;
  }

  updateSelectedQuantities(selectedQuantities) {
    this.selectedQuantities = selectedQuantities;
  }

  updateOrderPreview(orderPreviewDTO) {
    this.orderPreview = orderPreviewDTO;
  }
}
