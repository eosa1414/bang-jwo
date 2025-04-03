const hasBatchim = (word: string): boolean => {
    if (!word) return false;
    const lastChar = word[word.length - 1];
    const code = lastChar.charCodeAt(0) - 44032;
    const jong = code % 28;
    return jong !== 0;
  };
  
  export default hasBatchim;
  