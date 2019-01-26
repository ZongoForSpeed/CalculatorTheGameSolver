package com.game.calculator;

import com.game.calculator.operation.Portal;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SolverTest {
    @DataProvider
    public static Object[][] calculatorTheGame() {
        return new Object[][]{
                // goal, moves, value, operation, portal, expected
                {2, 2, 0, "+1", null, "+1 +1"}, // 1
                {8, 3, 0, "+2 +3", null, "+2 +3 +3"}, // 2
                {12, 3, 0, "*4 +1 +2", null, "+1 +2 *4"}, // 3
                {7, 3, 1, "+4 -2", null, "+4 +4 -2"}, // 4
                {4, 3, 3, "+4 *4 /4", null, "*4 +4 /4"}, // 5
                {64, 4, 0, "+2 *4", null, "+2 +2 *4 *4"}, // 6
                {5, 3, 4, "+3 /3 *3", null, "*3 +3 /3"}, // 7
                {4, 3, 4321, "<<", null, "<< << <<"}, // 8
                {4, 3, 0, "+8 *5 <<", null, "+8 *5 <<"}, // 9
                {9, 4, 50, "/5 *3 <<", null, "/5 *3 *3 <<"}, // 10

                {100, 3, 99, "-8 *11 <<", null, "-8 *11 <<"}, // 11
                {404, 5, 0, "+8 *10 /2", null, "+8 *10 *10 +8 /2"}, // 12
                {23, 4, 171, "*2 -9 <<", null, "-9 *2 << -9"}, // 13
                {21, 5, 0, "+5 *3 *5 <<", null, "+5 *3 *5 << *3"}, // 14
                {50, 3, 10, "*3 *2 -5", null, "*3 -5 *2"}, // 15
                {2, 5, 0, "+4 *9 <<", null, "+4 *9 << *9 <<"},  // 16
                {11, 2, 0, "1", null, "1 1"}, // 17
                {101, 3, 0, "1 0", null, "1 0 1"}, // 18
                {44, 3, 0, "2 *2", null, "2 2 *2"}, // 19
                {35, 2, 0, "+3 5", null, "+3 5"}, // 20

                {56, 3, 0, "1 +5", null, "+5 1 +5"}, // 21
                {9, 4, 0, "+2 /3 1", null, "+2 1 /3 +2"}, // 22
                {10, 4, 15, "0 +2 /5", null, "/5 +2 0 /5"}, // 23
                {210, 5, 0, "-5 +5 5 2", null, "2 5 -5 5 +5"}, // 24
                {2020, 4, 40, "0 +4 /2", null, "0 +4 0 /2"}, // 25
                {11, 4, 0, "12 <<", null, "12 << 12 <<"}, // 26
                {102, 4, 0, "10 +1 <<", null, "10 10 << +1"}, // 27
                {222, 4, 0, "1 1=>2", null, "1 1 1 1=>2"}, // 28
                {93, 4, 0, "+6 *7 6=>9", null, "+6 6=>9 *7 6=>9"}, // 29
                {2321, 6, 0, "1 2 1=>2 2=>3", null, "1 2 1 2=>3 1=>2 1"}, // 30

                {24, 5, 0, "+9 *2 8=>4", null, "+9 +9 8=>4 *2 8=>4"}, // 31
                {29, 5, 11, "/2 +3 1=>2 2=>9", null, "+3 1=>2 /2 2=>9 1=>2"}, // 32
                {20, 5, 36, "+3 /3 1=>2", null, "+3 +3 /3 +3 +3"}, // 33
                {15, 4, 2, "/3 1 *2 4=>5", null, "1 /3 *2 4=>5"}, // 34
                {414, 4, 1234, "23=>41 24=>14 12=>24 14=>2", null, "12=>24 24=>14 14=>2 23=>41"}, // 35
                {-85, 4, 0, "+6 5 -7", null, "+6 -7 -7 5"}, // 36
                {9, 3, 0, "-1 -2 x²", null, "-1 -2 x²"}, // 37
                {-120, 4, 0, "*5 -6 4", null, "4 -6 4 *5"}, // 38
                {144, 3, 0, "-1 2 x²", null, "-1 2 x²"}, // 39
                {5, 1, -5, "+/-", null, "+/-"}, // 40

                {-6, 3, 0, "+2 +4 +/-", null, "+2 +4 +/-"}, // 41
                {-13, 4, 0, "+3 -7 +/-", null, "+3 +3 +/- -7"}, // 42
                {60, 4, 0, "+5 -10 *4 +/-", null, "+5 +5 +5 *4"}, // 43
                {52, 5, 44, "+9 /2 *4 +/-", null, "/2 +/- +9 *4 +/-"}, // 44
                {10, 5, 9, "+5 *5 +/-", null, "+/- +5 +5 *5 +5"}, // 45
                {12, 5, 14, "6 +5 /8 +/-", null, "+/- +5 6 /8 +/-"}, // 46
                {13, 4, 55, "+9 +/- <<", null, "+/- << +9 +9"}, // 47
                {245, 5, 0, "-3 5 *4 +/-", null, "-3 -3 *4 5 +/-"}, // 48
                {12, 4, 39, "*-3 /3 +9 +/-", null, "/3 +/- +9 *-3"}, // 49
                {126, 6, 111, "*3 -9 +/- <<", null, "*3 +/- << -9 *3 +/-"}, // 50

                {3, 5, 34, "-5 +8 /7 +/-", null, "-5 +/- +8 /7 +/-"}, // 51
                {4, 5, 25, "-4 *-4 /3 /8 +/-", null, "-4 /3 -4 -4 *-4"}, // 52
                {21, 1, 12, "R", null, "Reverse"}, // 53
                {51, 3, 0, "+6 +9 R", null, "+6 +9 Reverse"}, // 54
                {101, 3, 100, "1 +9 R", null, "1 +9 Reverse"}, // 55
                {100, 4, 1101, "-1 R", null, "Reverse -1 Reverse -1"}, // 56
                {58, 4, 0, "+4 *4 -3 R", null, "+4 *4 Reverse -3"}, // 57
                {4, 3, 6, "1 /4 R", null, "1 Reverse /4"}, // 58
                {21, 3, 15, "+9 *5 R", null, "+9 *5 Reverse"}, // 59
                {13, 5, 100, "/2 R", null, "/2 /2 Reverse /2 /2"}, // 60

                {11011, 4, 10, "1 R", null, "1 1 Reverse 1"}, // 61
                {102, 4, 0, "10 *4 +5 R", null, "+5 *4 10 Reverse"}, // 62
                {7, 4, 0, "2 +1 /3 R", null, "+1 2 Reverse /3"}, // 63
                {4, 4, 0, "5 *4 *2 R", null, "5 *4 *2 Reverse"}, // 64
                {212, 3, 121, "2 -1 R", null, "-1 Reverse 2"}, // 65
                {9, 5, 8, "*3 1 /5 R", null, "1 Reverse *3 Reverse /5"}, // 66
                {13, 5, 0, "+7 +8 +9 R", null, "+7 +7 +8 +9 Reverse"}, // 67
                {123, 6, 0, "+3 1 -2 R", null, "+3 1 +3 -2 1 Reverse"}, // 68
                {424, 5, 0, "6 +8 R", null, "6 +8 Reverse 6 +8"}, // 69
                {81, 5, 7, "-9 *3 +4 +/- R", null, "-9 *3 *3 +/- Reverse"}, // 70

                {-43, 5, 0, "-5 +7 -9 R", null, "-5 -9 Reverse +7 -9"}, // 71
                {28, 7, 0, "+6 -3 R << ", null, "+6 +6 << +6 +6 Reverse -3"}, // 72
                {136, 5, 0, "1 +2 *3 R", null, "+2 1 *3 1 Reverse"}, // 73
                {-1, 4, 0, "+5 R +/-", null, "+5 +5 Reverse +/-"}, // 74
                {-25, 5, 0, "+4 *3 R +/-", null, "+4 *3 Reverse +4 +/-"}, // 75
                {-5, 5, 0, "+7 *3 R +/-", null, "+7 *3 Reverse +/- +7"}, // 76
                {41, 4, 88, "/4 -4 R", null, "/4 -4 -4 Reverse"}, // 77
                {101, 5, 100, "0 *2 2=>10 R 0=>1", null, "*2 Reverse 0 0=>1 2=>10"}, // 78
                {424, 7, 0, "/2 5 5=>4 R", null, "5 5=>4 /2 5 Reverse 5 5=>4"}, // 79
                {100, 5, 99, "9 /9 R 1=>0", null, "/9 9 Reverse 1=>0 /9"}, // 80

                {30, 5, 8, "2 -4 2=>3 R", null, "2 2=>3 Reverse -4 -4"}, // 81
                {222, 5, 101, "-1 R 0=>2", null, "-1 0=>2 Reverse -1 0=>2"}, // 82
                {500, 5, 36, "*4 /3 1=>5 R", null, "/3 1=>5 Reverse *4 1=>5"}, // 83
                {196, 8, 0, "1 +12 *13 R <<", null, "1 +12 *13 1 Reverse <<"}, // 84
                {101, 5, 50, "1=>10 +50 R 5=>1", null, "+50 +50 Reverse +50"}, // 85
                {2048, 6, 1, "2 *4 *10 R", null, "2 *4 Reverse *10 2 Reverse"}, // 86
                {123, 5, 12, "12 +1 12=>2 R", null, "12 +1 Reverse 12=>2 Reverse"}, // 87
                {55, 6, 86, "+2 +14 R 0=>5", null, "+14 Reverse +14 Reverse +2 +2"}, // 88
                {3, 4, 0, "1 S", null, "1 1 1 Sum"}, // 89
                {4, 4, 1231, "S 3=>1 2=>3", null, "2=>3 3=>1 Sum"}, // 90

                {45, 5, 0, "*9 4 *3 3=>5 S", null, "4 *3 Sum 3=>5 *9"}, // 91
                {28, 5, 424, "*4 4=>6 S", null, "*4 *4 Sum Sum *4"}, // 92
                {8, 4, 3, "3 +33 S 3=>1", null, "3 3=>1 +33 Sum"}, // 93
                {44, 4, 24, "/2 4 1=>2 S", null, "/2 1=>2 Sum 4"}, // 94
                {143, 4, 142, "*9 +9 44=>43 S", null, "Sum +9 *9 44=>43"}, // 95
                {1, 5, 24, "/3 *4 5=>10 S", null, "/3 *4 Sum 5=>10 Sum"}, // 96
                {100, 5, 4, "3 *3 +1 S", null, "*3 Sum 3 *3 +1"}, // 97
                {8, 5, 93, "+4 *3 S", null, "*3 +4 +4 Sum Sum"}, // 98
                {16, 5, 5, "*5 /2 S 5=>2", null, "*5 Sum *5 5=>2 /2"}, // 99
                {64, 4, 128, "*4 /4 S 5=>16", null, "/4 Sum 5=>16 *4"}, // 100

                {121, 6, 59, "1 *5 15=>51 S", null, "1 Sum *5 Sum 1"}, // 101
                {5, 6, 18, "*2 /3 12=>21 S", null, "*2 /3 12=>21 *2 /3 Sum"}, // 102
                {30, 4, 9, "-5 *-6 +/- S", null, "+/- -5 Sum *-6"}, // 103
                {-17, 5, 105, "-5 /5 *4 +/- S", null, "-5 *4 -5 +/- Sum"}, // 104
                {11, 6, 36, "-6 /3 +/- S", null, "-6 /3 Sum -6 -6 +/-"}, // 105
                {64, 5, 3, "+3 S x3 0=>1", null, "x^3 +3 0=>1 Sum x^3"}, // 106
                {11, 5, 2, "*2 10 x3 S 10=>1", null, "*2 x^3 *2 Sum"}, // 107
                {2311, 2, 1123, "<", null, "<Shift <Shift"}, // 108
                {3254, 2, 5432, ">", null, "Shift> Shift>"}, // 109

                {121, 3, 101, "+2 < >", null, "Shift> +2 <Shift"}, // 110
                {1999, 4, 98, "1 9 > 89=>99", null, "1 Shift> 9 89=>99"}, // 111
                {129, 4, 70, "*3 9 >", null, "*3 Shift> Shift> 9"}, // 112
                {210, 5, 120, "+1 < +/-", null, "+1 <Shift +/- +1 +/-"}, // 113
                {210, 5, 1001, "+2 > 12=>0", null, "Shift> +2 Shift> +2 12=>0"}, // 114
                {501, 3, 100, "+5 0 <", null, "+5 0 <Shift"}, // 115
                {3, 4, 212, "+11 3=>1 S <", null, "<Shift +11 3=>1 Sum"}, // 116
                {121, 4, 356, "-2 /3 >", null, "Shift> -2 /3 Shift>"}, // 117
                {13, 6, 2152, "25=>12 21=>3 12=>5 > R", null, "Reverse 25=>12 Shift> 12=>5 25=>12 21=>3"}, // 118
                {520, 5, 1025, "> 50=>0 25=>525 51=>5", null, "Shift> Shift> 25=>525 51=>5 50=>0"}, // 119

                {2332, 1, 23, "M", null, "Mirror"}, // 120
                {1221, 3, 0, "1 2 M", null, "1 2 Mirror"}, // 121
                {19, 6, 91, "+5 M S", null, "+5 +5 +5 Mirror +5 Sum"}, // 122
                {116, 4, 22, "-3 6 M S", null, "Sum -3 Mirror 6"}, // 123
                {20, 7, 125, "6=>2 0 M S", null, "Mirror Sum 6=>2 Mirror Sum 6=>2 0"}, // 124
                {3, 4, 22, "S /2 M <<", null, "/2 Mirror << Sum"}, // 125
                {1111, 5, 0, "+2 *6 M 21=>11", null, "+2 *6 Mirror 21=>11 21=>11"}, // 126
                {2020, 8, -1, "*3 +8 +2 R M", null, "+8 *3 Reverse +8 Mirror +8 +8 +2"}, // 127
                {112, 6, 13, "99=>60 /3 *3 M >", null, "*3 Mirror 99=>60 /3 Shift> Shift>"}, // 128
                {18, 6, 140, "-3 +9 /12 M <<", null, "<< << << +9 +9"}, // 129

                {33, 4, 17, "*2 -4 M <", null, "*2 -4 Mirror <Shift"}, // 130
                {20, 8, 125, "M S", null, "Mirror Sum Mirror Sum Sum Mirror Mirror Sum"}, // 131
                {15, 3, 10, "+2 [+1]", null, "+2 [+1] +3"}, // 132
                {14, 4, 0, "1 +2 [+1]", null, "1 1 [+1] +3"}, // 133
                {34, 4, 0, "2 3 [+1]", null, "3 [+1] 4"}, // 134
                {101, 5, 0, "2 +5 [+2]", null, "2 [+2] +7 4 +7"}, // 135
                {28, 5, 0, "1 +2 [+3]", null, "+2 1 +2 [+3] +5"}, // 136
                {42, 5, 0, "-2 +5 *2 [+1]", null, "+5 [+1] *3 *3 -3"}, // 137
                {25, 5, 0, "+2 *3 -3 [+2]", null, "+2 *3 [+2] *5 -5"}, // 138
                {41, 4, 5, "+4 +8 *3 [+2]", null, "[+2] *5 +6 +10"}, // 139

                {31, 5, 33, "*4 +2 +3 [+1] S", null, "Sum *4 +2 +2 +3"}, // 140
                {268, 5, 25, "+8 *2 *5 [+1]", null, "*2 *5 [+1] +9 +9"}, // 141
                {1111, 2, 1, "Store Paste", null, "Store Paste Store Paste"}, // 142
                {121, 4, 0, "+1 Store Paste", null, "+1 Store Paste +1 Paste"}, // 143
                {122, 4, 12, "Store Paste R <<", null, "Reverse Store Reverse Paste <<"}, // 144
                {17, 5, 10, "+2 /3 R Store Paste", null, "Reverse Store +2 +2 Paste /3"}, // 145
                {1234, 4, 23, "*2 -5 Store Paste <", null, "Store *2 -5 Paste <Shift"}, // 146
                {1025, 6, 125, "*2 Store Paste <<", null, "*2 << Store *2 *2 << Paste"}, // 147
                {115, 5, 23, "-8 Store Paste +/-", null, "-8 Store -8 -8 Paste +/-"}, // 148
                {16, 4, 15, "Store Paste 11=>33 R S", null, "Store Reverse Paste 11=>33 Sum"}, // 149

                {61, 7, 0, "5 << S Store Paste", null, "5 5 5 Sum Store Sum Paste <<"}, // 150
                {101, 5, 0, "*6 5 > Store Paste 3=>1", null, "5 *6 Store Paste Shift> 3=>1"}, // 151
                {12525, 5, 125, "1 /5 R Store Paste", null, "/5 Reverse Store Paste 1 Reverse"}, // 152
                {17, 6, 70, "8=>1 /2 0 Store Paste S", null, "/2 0 /2 Store Sum Paste Sum"}, // 153
                {101, 4, 12, "21=>0 12=>1 Store Paste M", null, "Store Mirror 21=>0 Paste 12=>1"}, // 154
                {3001, 7, 9, "39=>93 /3 Store Paste 31=>00", null, "Store /3 Paste 39=>93 Store Paste 39=>93 /3 31=>00"}, // 155
                {99, 3, 0, "1 I", null, "1 1 Inv10"}, // 156
                {2, 3, 1, "-1 I", null, "Inv10 -1 Inv10"}, // 157
                {15, 3, 14, "+5 *5 I", null, "+5 *5 Inv10"}, // 158
                {12, 3, 21, "-7 *5 I", null, "*5 -7 Inv10"}, // 159

                {13, 4, 67, "+3 R I", null, "+3 Reverse +3 +3"}, // 160
                {88, 5, 23, "-4 -2 R I", null, "-2 Reverse -2 Inv10 -2"}, // 161
                {105, 4, 5, "*3 /9 Store Paste I", null, "Store Paste *3 Inv10 /9"}, // 162
                {23, 4, 24, "+6 *3 R I", null, "*3 +6 Reverse Inv10"}, // 163
                {17, 4, 7, "+3 *3 *4 I", null, "+3 Inv10 +3 Inv10"}, // 164
                {21, 5, 35, "*9 /5 13=>10 I", null, "Inv10 *9 /5 13=>10 /5"}, // 165
                {18, 5, 9, "*3 S I", null, "*3 *3 *3 *3 Sum"}, // 166
                {101, 5, 12, "+4 I S", null, "+4 Inv10 Sum Inv10 +4"}, // 167
                {99, 6, 26, "2 S I", null, "2 Sum Inv10 2 Sum Inv10"}, // 168
                {13, 7, 15, "S I M", null, "Sum Inv10 Mirror Mirror Sum Inv10 Sum"}, // 169

                {99, 6, 78, "1=>6 6=>11 /6 I R", null, "/6 1=>6 Reverse /6 6=>11 Inv10"}, // 170
                {9, 4, 34, "*6 I <<", null, "<< *6 Inv10 <<"}, // 171
                {872, 8, 0, "8 88=>34 I <<", null, "8 Inv10 8 8 88=>34 << 8 Inv10"}, // 172
                {33, 5, 5, "*7 +8 -9 I *2", null, "+8 +8 *2 -9"}, // 173
                {23, 6, 12, "*5 S Store Paste I", null, "Store Inv10 Paste Paste Sum"}, // 174
                {1991, 4, 1, "Store Paste I", null, "Store Inv10 Paste Store Inv10 Paste"}, // 175
                {26, 4, 12, "<< S Store Paste I", null, "Store << Paste Inv10 Sum"}, // 176
                {48, 6, 51, "+6 *3 I R 4=>6", null, "+6 +6 Reverse +6 +6"}, // 177
                {1, 6, 0, "+5 *3 /6 I R", null, "+5 +5 Reverse"}, // 178
                {777, 5, 369, "93=>63 63=>33 I 36=>93 39=>33", null, "36=>93 93=>63 63=>33 39=>33 Inv10"}, // 179

                {10, 3, 99, "1 -1", new Portal(2, 0), "1 1 -1"}, // 180
                {64, 2, 9, "4 6", new Portal(2, 1), "6 4"}, // 181
                {35, 3, 50, "+5 *3 *5", new Portal(2, 1), "*3 *5 +5"}, // 182
                {131, 4, 306, "3 +1 *2", new Portal(3, 0), "*2 3 +1 +1"}, // 183
                {123, 5, 321, "/2 1 0 3", new Portal(3, 0), "1 0 3 /2 1"}, // 184
                {150, 5, 525, "+1 6 7 /2", new Portal(3, 0), "7 6 /2 7"}, // 185
                {212, 5, 301, "10 -2 3", new Portal(3, 0), "10 10 10 -2"}, // 186
                {13, 4, 99, "S M I", new Portal(3, 1), "Sum Inv10 Mirror Sum"}, // 187
                {822, 5, 25, "M 5 Store Paste <<", new Portal(3, 1), "5 Mirror Store Paste"}, // 188
                {516, 4, 45, "+10 M R", new Portal(3, 1), "+10 Mirror +10 Reverse"}, // 189

                {212, 4, 238, "28=>21 -5 I >", null, "Inv10 Shift> 28=>21 -5"}, // 190
                {90, 5, 58, "*6 I >", null, "Inv10 Shift> *6 *6 Shift>"}, // 191
                {500, 6, 189, "+8 *4 9 I 7=>0", new Portal(3, 0), "*4 7=>0 9 +8 7=>0"}, // 192
                {321, 4, 234, "9 +9 53=>32", new Portal(3, 0), "9 9 +9 53=>32"}, // 193
                {123, 4, 333, "1 3 /2 [+1]", new Portal(3, 0), "3 [+1] /3 2"}, // 194
                {777, 5, 613, "5 *2 +3 R I", new Portal(3, 0), "*2 5 5"}, // 195
                {550, 7, 60, "+5 *5 2 I", new Portal(3, 1), "+5 +5 2 Inv10 *5"}, // 196
                {4321, 5, 1234, "24=>13 12=>32 13=>21 23=>32 23=>43", null, "23=>32 24=>13 13=>21 12=>32 23=>43"}, // 197
                {750, 7, 4, "+6 4 *3 I", new Portal(3, 1), "+6 Inv10 4 +6 *3"}, // 198
                {3507, 6, 3002, "7 3=>5 I >", new Portal(4, 0), "7 7 3=>5 Inv10 Shift> 7"}, // 199
        };
    }

    @Test(dataProvider = "calculatorTheGame")
    public void calculatorTheGame(int goal, int moves, int value, String operation, Portal portal, String expectedResult) {
        Assertions.assertThat(Solver.solve(goal, moves, value, operation, portal)).isEqualTo(expectedResult);
    }
}