class SymCross {
    constructor() {
        this.words = Array(4).fill('XXXX');
    }

    setWord(row, word) {
        word = word.toUpperCase();
        
        if (row < 0 || row > 3) {
            throw new Error('Row index is not valid');
        }
        
        if (word.length !== 4) {
            throw new Error('Word length is not 4');
        }
        
        this.words[row] = word;
    }

    getRow(row) {
        if (row < 0 || row > 3) {
            throw new Error('Row index not valid');
        }
        
        return this.words[row];
    }

    getColumn(col) {
        if (col < 0 || col > 3) {
            throw new Error('Column index not valid');
        }
        
        let result = '';
        for (let i = 0; i < 4; i++) {
            result += this.words[i].charAt(col);
        }
        
        return result;
    }

    isSymmetric() {
        for (let i = 0; i < 4; i++) {
            if (this.getRow(i) !== this.getColumn(i)) {
                return false;
            }
        }
        
        return true;
    }

    isComplete(dictionary) {
        if (!this.isSymmetric()) {
            return false;
        }
        
        for (let i = 0; i < 4; i++) {
            if (!dictionary.contains(this.words[i])) {
                return false;
            }
        }
        
        return true;
    }

    static async makeCrossword(first, dictionary) {
        first = first.toUpperCase();
        const puzzle = new SymCross();
        
        if (!dictionary.contains(first)) {
            return puzzle;
        }
        
        puzzle.setWord(0, first);
        
        const col2Words = dictionary.startsWith(first.charAt(1));
        const col3Words = dictionary.startsWith(first.charAt(2));
        const col4Words = dictionary.startsWith(first.charAt(3));
        
        for (const word1 of col2Words) {
            puzzle.setWord(1, word1);
            for (const word2 of col3Words) {
                puzzle.setWord(2, word2);
                for (const word3 of col4Words) {
                    puzzle.setWord(3, word3);
                    if (puzzle.isSymmetric()) {
                        return puzzle;
                    }
                }
            }
        }
        
        // Reset if no solution found
        puzzle.words = Array(4).fill('XXXX');
        return puzzle;
    }

    toString() {
        return this.words.join('\n');
    }
}