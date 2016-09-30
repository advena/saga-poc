# saga-poc
Simple flow to change the validity period for a game that user has bought. 
The game abonament can be extended in past and future. There is no constrains about that.
Every change on validity period that is combined with user game should also generate/remove payments for that game and also update user points balance

If user hasn't got enough points on his balance the validity period change cannot be applied
