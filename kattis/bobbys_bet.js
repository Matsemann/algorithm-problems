/*
Using binomial for each possible occurence and summing those.

For instance rolling 5 out of 6 means 0.333 chance
At least to out of three times means adding the odds of
bio(p=0.3333, n=2, k=3) + bio(p=0.3333, n=3, k=3)
 */

function print(input) {
    console.log(input);
}

var readCount = 0;

var input = [
    '3',
    '2 2 9 10 100',
    '1 2 10 10 1',
    '1 2 10 10 2'
];


function readline() {
    return input[readCount++];
}

function bobbysBet() {
    var cases = parseInt(readline());

    for (var i = 0; i < cases; i++) {
        var v = readline().split(' ');
        shouldIDoIt(parseInt(v[0]), parseInt(v[1]), parseInt(v[2]), parseInt(v[3]), parseInt(v[4]));
    }

}

function shouldIDoIt(R, S, X, Y, W) {
    var p = (S - R + 1) / S;

    var odds = 0;

    for (var i = X; i <= Y; i++) {
        var chance = chanceOfExactly(p, i, Y);
        odds += chance;
    }

    if (odds * W <= 1) {
        print('no');
    } else {
        print('yes');
    }

    return odds;
}

function chanceOfExactly(p, k, n) {
    var bi = binomialCoefficient(n, k);
    var pk = Math.pow(p, k);
    var pInv = 1 - p;
    var pInvNK = Math.pow(pInv, n - k);

    return bi * pk * pInvNK;
}


function binomialCoefficient(a, b) {
    var numerator = fact(a);
    var denominator = fact(a - b) * fact(b);
    return numerator / denominator;
}

function fact(x) {
    if (x == 0) return 1;
    return x * fact(x - 1);
}

bobbysBet();
