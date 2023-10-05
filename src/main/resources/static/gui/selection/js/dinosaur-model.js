/**
 * Fetches a list of dinosaur cards from the API.
 * @returns {Array} - An array of dino card data, or throws an error if the fetch operation fails.
 */
async function fetchDinosaurs() {
  try {
    // Attempt to fetch data from the API
    const response = await fetch('/api/v1/dinosaurs/dino-cards');

    // Check for HTTP errors
    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(`Failed to fetch dinosaur data: ${errorData.message || ''}`);
    }

    // Parse the JSON payload from the successful response
    const data = await response.json();

    return data.dinoCardDataList;

  } catch (error) {
    console.error('An error occurred:', error);
    throw error;
  }
}


