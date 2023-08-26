const coffeeQuotes = [
  {
    quote: "Without my morning coffee, I'm just like a dried-up piece of goat.",
    author: "Johann Sebastian Bach"
  },
  {
    quote: "It is inhumane, in my opinion, to force people who have a genuine medical need for coffee to wait in line behind people who apparently view it as some kind of recreational activity.",
    author: "Dave Barry"
  },
  {
    quote: "As soon as coffee is in your stomach, there is a general commotion. Ideas begin to move… similes arise, the paper is covered. Coffee is your ally and writing ceases to be a struggle.",
    author: "Honoré de Balzac"
  },
  {
    quote: "I have measured out my life with coffee spoons.",
    author: "T.S. Eliot"
  },
  {
    quote: "We want to do a lot of stuff; we're not in great shape. We didn't get a good night's sleep. We're a little depressed. Coffee solves all these problems in one delightful little cup.",
    author: "Jerry Seinfeld"
  },
  {
    quote: "Good communication is just as stimulating as black coffee, and just as hard to sleep after.",
    author: "Anne Morrow Lindbergh"
  },
  {
    quote: "It was a great conversation starter, 'Hello, I love coffee,' because everyone loves coffee.",
    author: "F. Scott Fitzgerald"
  },
  {
    quote: "I think if I were a woman, I'd wear coffee as a perfume.",
    author: "Annette Bening"
  },
  {
    quote: "Coffee is real good when you drink it; it gives you time to think. It's a lot more than just a drink; it's something happening.",
    author: "Gertrude Stein"
  },
  {
    quote: "The powers of a man's mind are directly proportioned to the quantity of coffee he drinks.",
    author: "Sir James Mackintosh"
  }
];

// Define a function called getRandomQuote to retrieve a random quote from the coffeeQuotes array.
function getRandomQuote() {
  const randomIndex = Math.floor(Math.random() * coffeeQuotes.length);
  return coffeeQuotes[randomIndex];
}

const randomQuote = getRandomQuote();

// Find the div element with the ID 'random-quote' to display the quote.
const quoteDiv = document.getElementById('random-quote');

// Populate the 'quoteDiv' with the random quote and its author using HTML markup.
quoteDiv.innerHTML = `<h1>"${randomQuote.quote}"</h1><p>- ${randomQuote.author}</p>`;
