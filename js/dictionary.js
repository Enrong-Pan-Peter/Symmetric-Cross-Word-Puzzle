class Dictionary {
    constructor() {
        this.words = [];
        this.indexOfLetter = Array(26).fill(-1);
    }

    async loadDictionary() {
        try {
            const response = await fetch('data/four.txt');
            const text = await response.text();
            this.words = text.split('\n').filter(word => word.trim().length > 0);
            
            // Initialize the index of first occurrence for each letter
            for (let i = 0; i < this.words.length; i++) {
                const firstLetter = this.words[i].charAt(0);
                const letterIndex = firstLetter.charCodeAt(0) - 'A'.charCodeAt(0);
                
                if (letterIndex >= 0 && letterIndex < 26 && this.indexOfLetter[letterIndex] === -1) {
                    this.indexOfLetter[letterIndex] = i;
                }
            }
            
            return true;
        } catch (error) {
            console.error('Error loading dictionary:', error);
            return false;
        }
    }

    contains(word) {
        word = word.toUpperCase();
        return this.words.includes(word);
    }

    indexOf(c) {
        const charCode = typeof c === 'string' ? c.charCodeAt(0) : c;
        
        if (charCode < 'A'.charCodeAt(0)) {
            return 0;
        }
        
        if (charCode > 'Z'.charCodeAt(0)) {
            return this.words.length;
        }
        
        const letterIndex = charCode - 'A'.charCodeAt(0);
        return this.indexOfLetter[letterIndex];
    }

    startsWith(prefix) {
        prefix = prefix.toUpperCase();
        
        if (prefix === '') {
            return [...this.words]; // Return a copy of all words
        }
        
        if (prefix.length > 4) {
            return [];
        }
        
        const firstChar = prefix.charAt(0);
        const startIndex = this.indexOf(firstChar);
        const endIndex = this.indexOf(firstChar.charCodeAt(0) + 1);
        
        const result = [];
        
        for (let i = startIndex; i < endIndex && i < this.words.length; i++) {
            const word = this.words[i];
            let matches = true;
            
            for (let j = 0; j < prefix.length; j++) {
                if (word.charAt(j) !== prefix.charAt(j)) {
                    matches = false;
                    break;
                }
            }
            
            if (matches) {
                result.push(word);
            }
        }
        
        return result;
    }
}