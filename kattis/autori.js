
// Utils
/*
function print(input) {
    console.log(input);
}

var readCount = 0;

var input = [
    'Knuth-Morris-Pratt'
];

function readline() {
    return input[readCount++];
}



*/



// Problem solving

const names = readline();

const result = names.split('-')
    .map(n => n.substr(0, 1))
    .join("");

print(result);