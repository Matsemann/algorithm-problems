/*
Simple bruteforce trying each permutation, as it's only 6 boxes
6! = 720 combinations
 */
function print(input) {
    console.log(input);
}

var readCount = 0;

var input = [
    '12 17 36 37 51 63 92 124'
];

function readline() {
    return input[readCount++];
}


function towering() {
    var input = readline().split(' ');
    var boxes = [];
    for (var i = 0; i < 6; i++) {
        boxes.push(parseInt(input[i]));
    }
    var tower1height = parseInt(input[6]);
    var tower2height = parseInt(input[7]);

    var permutations = permutate(boxes);

    for (var j = 0; j < permutations.length; j++) {
        if (testPermutation(permutations[j], tower1height, tower2height)) {
            print(permutations[j].join(' '));
            break;
        }
    }

}

function testPermutation(perm, height1, height2) {
    if (perm[0] < perm[1] || perm[1] < perm[2] || perm[3] < perm[4] || perm[4] < perm[5]) {
        return false;
    }
    return (perm[0] + perm[1] + perm[2] == height1) && (perm[3] + perm[4] + perm[5] == height2);
}

function permutate(arr) {
    return arr.reduce(function permute(res, item, key, arr) {
        return res.concat(arr.length > 1 && arr.slice(0, key).concat(arr.slice(key + 1)).reduce(permute, []).map(function(perm) { return [item].concat(perm); }) || item);
    }, []);
}

towering();