// Function to fetch dinosaurs from API
async function fetchDinosaurs() {
  try {
    const response = await fetch('/api/v1/dinosaurs');
    if (response.ok) {
      return await response.json();
    } else {
      console.error('Failed to fetch dinosaur data');
      return null;
    }
  } catch (error) {
    console.error('An error occurred:', error);
    return null;
  }
}
