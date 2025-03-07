document.addEventListener('DOMContentLoaded', async () => {
    const dictionary = new Dictionary();
    const status = document.getElementById('status');
    const crosswordElement = document.getElementById('crossword');
    const generateBtn = document.getElementById('generateBtn');
    const randomBtn = document.getElementById('randomBtn');
    const firstWordInput = document.getElementById('firstWord');
    
    // Disable buttons until dictionary is loaded
    generateBtn.disabled = true;
    randomBtn.disabled = true;
    
    // Show loading status
    showStatus('Loading dictionary...', 'info');
    
    const dictionaryLoaded = await dictionary.loadDictionary();
    
    if (!dictionaryLoaded) {
        showStatus('Failed to load dictionary!', 'error');
        return;
    }
    
    showStatus('Dictionary loaded successfully!', 'success');
    setTimeout(() => { status.classList.add('hidden'); }, 2000);
    
    // Enable buttons
    generateBtn.disabled = false;
    randomBtn.disabled = false;
    
    // Function to show status messages
    function showStatus(message, type) {
        status.textContent = message;
        status.classList.remove('hidden', 'bg-green-100', 'text-green-800', 'bg-red-100', 'text-red-800', 'bg-blue-100', 'text-blue-800');
        
        switch(type) {
            case 'success':
                status.classList.add('bg-green-100', 'text-green-800');
                break;
            case 'error':
                status.classList.add('bg-red-100', 'text-red-800');
                break;
            case 'info':
            default:
                status.classList.add('bg-blue-100', 'text-blue-800');
                break;
        }
    }
    
    // Display the crossword grid
    function displayCrossword(puzzle) {
        crosswordElement.innerHTML = '';
        
        for (let i = 0; i < 4; i++) {
            for (let j = 0; j < 4; j++) {
                const cell = document.createElement('div');
                cell.className = 'flex items-center justify-center bg-white font-bold text-2xl md:text-3xl text-gray-800 aspect-square transition-all';
                
                // Add animation effect by delaying each cell slightly
                const delay = (i * 4 + j) * 50;
                cell.style.animation = `fadeIn 0.5s ease ${delay}ms forwards`;
                cell.style.opacity = "0";
                
                cell.textContent = puzzle.getRow(i).charAt(j);
                crosswordElement.appendChild(cell);
            }
        }
        
        // Add keyframe animation to the document if it doesn't exist
        if (!document.querySelector('#crossword-animations')) {
            const style = document.createElement('style');
            style.id = 'crossword-animations';
            style.textContent = `
                @keyframes fadeIn {
                    from { opacity: 0; transform: scale(0.8); }
                    to { opacity: 1; transform: scale(1); }
                }
            `;
            document.head.appendChild(style);
        }
    }
    
    // Generate a crossword from user input
    generateBtn.addEventListener('click', async () => {
        const firstWord = firstWordInput.value.trim().toUpperCase();
        
        if (firstWord.length !== 4) {
            showStatus('Please enter a 4-letter word!', 'error');
            return;
        }
        
        if (!dictionary.contains(firstWord)) {
            showStatus(`"${firstWord}" is not in the dictionary!`, 'error');
            return;
        }
        
        // Add loading state to button
        generateBtn.disabled = true;
        generateBtn.innerHTML = '<svg class="animate-spin -ml-1 mr-2 h-4 w-4 text-white inline" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"><circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle><path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path></svg> Generating...';
        
        showStatus('Generating puzzle...', 'info');
        
        // Small timeout to allow UI to update
        setTimeout(async () => {
            const puzzle = await SymCross.makeCrossword(firstWord, dictionary);
            
            if (puzzle.isComplete(dictionary)) {
                showStatus(`Crossword generated successfully with "${firstWord}"!`, 'success');
            } else {
                showStatus(`No valid crossword found starting with "${firstWord}"!`, 'error');
            }
            
            displayCrossword(puzzle);
            
            // Restore button
            generateBtn.disabled = false;
            generateBtn.textContent = 'Generate';
        }, 100);
    });
    
    // Generate a random crossword
    randomBtn.addEventListener('click', async () => {
        // Add loading state to button
        randomBtn.disabled = true;
        randomBtn.innerHTML = '<svg class="animate-spin -ml-1 mr-2 h-4 w-4 text-white inline" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"><circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle><path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path></svg> Generating...';
        
        showStatus('Generating random puzzle...', 'info');
        
        // Get a random word from the dictionary
        const randomIndex = Math.floor(Math.random() * dictionary.words.length);
        const randomWord = dictionary.words[randomIndex];
        firstWordInput.value = randomWord;
        
        // Small timeout to allow UI to update
        setTimeout(async () => {
            const puzzle = await SymCross.makeCrossword(randomWord, dictionary);
            
            if (puzzle.isComplete(dictionary)) {
                showStatus(`Crossword generated successfully with "${randomWord}"!`, 'success');
            } else {
                showStatus(`No valid crossword found with "${randomWord}". Trying another word...`, 'info');
                
                // Restore button and try again
                randomBtn.disabled = false;
                randomBtn.textContent = 'Random';
                randomBtn.click();
                return;
            }
            
            displayCrossword(puzzle);
            
            // Restore button
            randomBtn.disabled = false;
            randomBtn.textContent = 'Random';
        }, 100);
    });
    
    // Initialize with a default crossword
    const defaultPuzzle = new SymCross();
    displayCrossword(defaultPuzzle);
    
    // Add keyboard shortcut - Enter key to generate
    firstWordInput.addEventListener('keyup', function(event) {
        if (event.key === 'Enter') {
            generateBtn.click();
        }
    });
});