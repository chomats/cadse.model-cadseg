/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.cadseg.exp;
import java.util.Collections;
import java.util.List;

/** Token Manager. */
public class ExpressionParseTokenManager implements ExpressionParseConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_3(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_3(int pos, long active0)
{
   return jjMoveNfa_3(jjStopStringLiteralDfa_3(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_3()
{
   switch(curChar)
   {
      case 36:
         return jjMoveStringLiteralDfa1_3(0x2000000L);
      default :
         return jjMoveNfa_3(6, 0);
   }
}
private int jjMoveStringLiteralDfa1_3(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_3(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 123:
         if ((active0 & 0x2000000L) != 0L)
            return jjStopAtPos(1, 25);
         break;
      default :
         break;
   }
   return jjStartNfa_3(0, active0);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_3(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 14;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
               case 0:
                  if ((0xffffffebffffffffL & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 2:
                  if (curChar != 36)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 5:
                  if (curChar != 34)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 7:
                  if ((0xffffffebffffffffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 10:
                  if (curChar == 36)
                     jjCheckNAddStates(0, 2);
                  break;
               case 13:
                  if (curChar == 34)
                     jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
                  if ((0xd7ffffffffffffffL & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                     jjCheckNAddTwoStates(0, 1);
                  }
                  else if (curChar == 123)
                     jjCheckNAddTwoStates(7, 9);
                  if (curChar == 92)
                     jjAddStates(3, 6);
                  break;
               case 0:
                  if ((0xd7ffffffffffffffL & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 1:
                  if (curChar == 92)
                     jjAddStates(3, 6);
                  break;
               case 3:
                  if (curChar != 123)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 4:
                  if (curChar != 125)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 7:
                  if ((0xd7ffffffffffffffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 8:
                  if (curChar == 125 && kind > 27)
                     kind = 27;
                  break;
               case 9:
                  if (curChar == 92)
                     jjAddStates(7, 10);
                  break;
               case 11:
                  if (curChar == 123)
                     jjCheckNAddStates(0, 2);
                  break;
               case 12:
                  if (curChar == 125)
                     jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 6:
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjCheckNAddTwoStates(0, 1);
                  break;
               case 7:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 14 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_2(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_2(int pos, long active0)
{
   return jjMoveNfa_2(jjStopStringLiteralDfa_2(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      case 61:
         return jjStopAtPos(0, 24);
      case 93:
         return jjStopAtPos(0, 23);
      default :
         return jjMoveNfa_2(0, 0);
   }
}
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 1;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xdfffbfefffffffffL & l) == 0L)
                     break;
                  kind = 22;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xdfffffffd7ffffffL & l) == 0L)
                     break;
                  kind = 22;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 1 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_1(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0xfffc0L) != 0L)
         {
            jjmatchedKind = 20;
            return 0;
         }
         return -1;
      case 1:
         if ((active0 & 0xfffc0L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 1;
            return 0;
         }
         return -1;
      case 2:
         if ((active0 & 0x1000L) != 0L)
            return 0;
         if ((active0 & 0xfefc0L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 2;
            return 0;
         }
         return -1;
      case 3:
         if ((active0 & 0xfefc0L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 3;
            return 0;
         }
         return -1;
      case 4:
         if ((active0 & 0x4000L) != 0L)
            return 0;
         if ((active0 & 0xfafc0L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 4;
            return 0;
         }
         return -1;
      case 5:
         if ((active0 & 0xfafc0L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 5;
            return 0;
         }
         return -1;
      case 6:
         if ((active0 & 0xfa000L) != 0L)
         {
            if (jjmatchedPos != 6)
            {
               jjmatchedKind = 20;
               jjmatchedPos = 6;
            }
            return 0;
         }
         if ((active0 & 0xfc0L) != 0L)
            return 0;
         return -1;
      case 7:
         if ((active0 & 0xfaf80L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 7;
            return 0;
         }
         return -1;
      case 8:
         if ((active0 & 0xfaf80L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 8;
            return 0;
         }
         return -1;
      case 9:
         if ((active0 & 0x40080L) != 0L)
            return 0;
         if ((active0 & 0xbaf00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 9;
            return 0;
         }
         return -1;
      case 10:
         if ((active0 & 0x10000L) != 0L)
            return 0;
         if ((active0 & 0xaaf00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 10;
            return 0;
         }
         return -1;
      case 11:
         if ((active0 & 0x8000L) != 0L)
            return 0;
         if ((active0 & 0xa2f00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 11;
            return 0;
         }
         return -1;
      case 12:
         if ((active0 & 0x20000L) != 0L)
            return 0;
         if ((active0 & 0x82f00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 12;
            return 0;
         }
         return -1;
      case 13:
         if ((active0 & 0x82f00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 13;
            return 0;
         }
         return -1;
      case 14:
         if ((active0 & 0x82000L) != 0L)
            return 0;
         if ((active0 & 0xf00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 14;
            return 0;
         }
         return -1;
      case 15:
         if ((active0 & 0xf00L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 15;
            return 0;
         }
         return -1;
      case 16:
         if ((active0 & 0x800L) != 0L)
            return 0;
         if ((active0 & 0x700L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 16;
            return 0;
         }
         return -1;
      case 17:
         if ((active0 & 0x200L) != 0L)
            return 0;
         if ((active0 & 0x500L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 17;
            return 0;
         }
         return -1;
      case 18:
         if ((active0 & 0x100L) != 0L)
            return 0;
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 18;
            return 0;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_1(int pos, long active0)
{
   return jjMoveNfa_1(jjStopStringLiteralDfa_1(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 35:
         return jjMoveStringLiteralDfa1_1(0xfffc0L);
      case 46:
         return jjStopAtPos(0, 4);
      case 91:
         return jjStopAtPos(0, 21);
      case 125:
         return jjStopAtPos(0, 5);
      default :
         return jjMoveNfa_1(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 100:
         return jjMoveStringLiteralDfa2_1(active0, 0x20000L);
      case 105:
         return jjMoveStringLiteralDfa2_1(active0, 0x1000L);
      case 108:
         return jjMoveStringLiteralDfa2_1(active0, 0x80000L);
      case 110:
         return jjMoveStringLiteralDfa2_1(active0, 0x4000L);
      case 112:
         return jjMoveStringLiteralDfa2_1(active0, 0xfc0L);
      case 113:
         return jjMoveStringLiteralDfa2_1(active0, 0x2000L);
      case 115:
         return jjMoveStringLiteralDfa2_1(active0, 0x10000L);
      case 116:
         return jjMoveStringLiteralDfa2_1(active0, 0x40000L);
      case 117:
         return jjMoveStringLiteralDfa2_1(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_1(0, active0);
}
private int jjMoveStringLiteralDfa2_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_1(active0, 0x4fc0L);
      case 100:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_1(2, 12, 0);
         break;
      case 104:
         return jjMoveStringLiteralDfa3_1(active0, 0x10000L);
      case 105:
         return jjMoveStringLiteralDfa3_1(active0, 0xa0000L);
      case 110:
         return jjMoveStringLiteralDfa3_1(active0, 0x8000L);
      case 117:
         return jjMoveStringLiteralDfa3_1(active0, 0x2000L);
      case 121:
         return jjMoveStringLiteralDfa3_1(active0, 0x40000L);
      default :
         break;
   }
   return jjStartNfa_1(1, active0);
}
private int jjMoveStringLiteralDfa3_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_1(active0, 0x2000L);
      case 105:
         return jjMoveStringLiteralDfa4_1(active0, 0x8000L);
      case 109:
         return jjMoveStringLiteralDfa4_1(active0, 0x4000L);
      case 110:
         return jjMoveStringLiteralDfa4_1(active0, 0x80000L);
      case 111:
         return jjMoveStringLiteralDfa4_1(active0, 0x10000L);
      case 112:
         return jjMoveStringLiteralDfa4_1(active0, 0x40000L);
      case 114:
         return jjMoveStringLiteralDfa4_1(active0, 0xfc0L);
      case 115:
         return jjMoveStringLiteralDfa4_1(active0, 0x20000L);
      default :
         break;
   }
   return jjStartNfa_1(2, active0);
}
private int jjMoveStringLiteralDfa4_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_1(4, 14, 0);
         return jjMoveStringLiteralDfa5_1(active0, 0x40fc0L);
      case 107:
         return jjMoveStringLiteralDfa5_1(active0, 0x80000L);
      case 108:
         return jjMoveStringLiteralDfa5_1(active0, 0x2000L);
      case 112:
         return jjMoveStringLiteralDfa5_1(active0, 0x20000L);
      case 113:
         return jjMoveStringLiteralDfa5_1(active0, 0x8000L);
      case 114:
         return jjMoveStringLiteralDfa5_1(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_1(3, active0);
}
private int jjMoveStringLiteralDfa5_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa6_1(active0, 0xc0000L);
      case 105:
         return jjMoveStringLiteralDfa6_1(active0, 0x2000L);
      case 108:
         return jjMoveStringLiteralDfa6_1(active0, 0x20000L);
      case 110:
         return jjMoveStringLiteralDfa6_1(active0, 0xfc0L);
      case 116:
         return jjMoveStringLiteralDfa6_1(active0, 0x10000L);
      case 117:
         return jjMoveStringLiteralDfa6_1(active0, 0x8000L);
      default :
         break;
   }
   return jjStartNfa_1(4, active0);
}
private int jjMoveStringLiteralDfa6_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa7_1(active0, 0x10000L);
      case 97:
         return jjMoveStringLiteralDfa7_1(active0, 0x20000L);
      case 101:
         return jjMoveStringLiteralDfa7_1(active0, 0x8000L);
      case 102:
         return jjMoveStringLiteralDfa7_1(active0, 0x2000L);
      case 110:
         return jjMoveStringLiteralDfa7_1(active0, 0x40000L);
      case 116:
         if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 6;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_1(active0, 0x80f80L);
      default :
         break;
   }
   return jjStartNfa_1(5, active0);
}
private int jjMoveStringLiteralDfa7_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa8_1(active0, 0x8f80L);
      case 97:
         return jjMoveStringLiteralDfa8_1(active0, 0x40000L);
      case 105:
         return jjMoveStringLiteralDfa8_1(active0, 0x2000L);
      case 110:
         return jjMoveStringLiteralDfa8_1(active0, 0x10000L);
      case 121:
         return jjMoveStringLiteralDfa8_1(active0, 0xa0000L);
      default :
         break;
   }
   return jjStartNfa_1(6, active0);
}
private int jjMoveStringLiteralDfa8_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa9_1(active0, 0x20000L);
      case 97:
         return jjMoveStringLiteralDfa9_1(active0, 0x10000L);
      case 100:
         return jjMoveStringLiteralDfa9_1(active0, 0x400L);
      case 101:
         return jjMoveStringLiteralDfa9_1(active0, 0x2000L);
      case 105:
         return jjMoveStringLiteralDfa9_1(active0, 0x80L);
      case 109:
         return jjMoveStringLiteralDfa9_1(active0, 0x40000L);
      case 110:
         return jjMoveStringLiteralDfa9_1(active0, 0x8000L);
      case 112:
         return jjMoveStringLiteralDfa9_1(active0, 0x80000L);
      case 115:
         return jjMoveStringLiteralDfa9_1(active0, 0x200L);
      case 116:
         return jjMoveStringLiteralDfa9_1(active0, 0x800L);
      case 117:
         return jjMoveStringLiteralDfa9_1(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_1(7, active0);
}
private int jjMoveStringLiteralDfa9_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa10_1(active0, 0x8000L);
      case 100:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_1(9, 7, 0);
         return jjMoveStringLiteralDfa10_1(active0, 0x2000L);
      case 101:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_1(9, 18, 0);
         return jjMoveStringLiteralDfa10_1(active0, 0x80000L);
      case 104:
         return jjMoveStringLiteralDfa10_1(active0, 0x200L);
      case 105:
         return jjMoveStringLiteralDfa10_1(active0, 0x400L);
      case 109:
         return jjMoveStringLiteralDfa10_1(active0, 0x10000L);
      case 110:
         return jjMoveStringLiteralDfa10_1(active0, 0x20100L);
      case 121:
         return jjMoveStringLiteralDfa10_1(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_1(8, active0);
}
private int jjMoveStringLiteralDfa10_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa11_1(active0, 0x82000L);
      case 97:
         return jjMoveStringLiteralDfa11_1(active0, 0x20000L);
      case 101:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_1(10, 16, 0);
         break;
      case 105:
         return jjMoveStringLiteralDfa11_1(active0, 0x100L);
      case 109:
         return jjMoveStringLiteralDfa11_1(active0, 0x8000L);
      case 111:
         return jjMoveStringLiteralDfa11_1(active0, 0x200L);
      case 112:
         return jjMoveStringLiteralDfa11_1(active0, 0x800L);
      case 115:
         return jjMoveStringLiteralDfa11_1(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_1(9, active0);
}
private int jjMoveStringLiteralDfa11_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_1(11, 15, 0);
         return jjMoveStringLiteralDfa12_1(active0, 0x800L);
      case 109:
         return jjMoveStringLiteralDfa12_1(active0, 0x20000L);
      case 110:
         return jjMoveStringLiteralDfa12_1(active0, 0x82000L);
      case 112:
         return jjMoveStringLiteralDfa12_1(active0, 0x400L);
      case 113:
         return jjMoveStringLiteralDfa12_1(active0, 0x100L);
      case 114:
         return jjMoveStringLiteralDfa12_1(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_1(10, active0);
}
private int jjMoveStringLiteralDfa12_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa13_1(active0, 0x800L);
      case 97:
         return jjMoveStringLiteralDfa13_1(active0, 0x82000L);
      case 101:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_1(12, 17, 0);
         break;
      case 108:
         return jjMoveStringLiteralDfa13_1(active0, 0x400L);
      case 116:
         return jjMoveStringLiteralDfa13_1(active0, 0x200L);
      case 117:
         return jjMoveStringLiteralDfa13_1(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_1(11, active0);
}
private int jjMoveStringLiteralDfa13_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(11, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(12, active0);
      return 13;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa14_1(active0, 0x200L);
      case 97:
         return jjMoveStringLiteralDfa14_1(active0, 0x400L);
      case 101:
         return jjMoveStringLiteralDfa14_1(active0, 0x100L);
      case 109:
         return jjMoveStringLiteralDfa14_1(active0, 0x82000L);
      case 110:
         return jjMoveStringLiteralDfa14_1(active0, 0x800L);
      default :
         break;
   }
   return jjStartNfa_1(12, active0);
}
private int jjMoveStringLiteralDfa14_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(12, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(13, active0);
      return 14;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa15_1(active0, 0x100L);
      case 97:
         return jjMoveStringLiteralDfa15_1(active0, 0x800L);
      case 101:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_1(14, 13, 0);
         else if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_1(14, 19, 0);
         break;
      case 110:
         return jjMoveStringLiteralDfa15_1(active0, 0x200L);
      case 121:
         return jjMoveStringLiteralDfa15_1(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_1(13, active0);
}
private int jjMoveStringLiteralDfa15_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(13, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(14, active0);
      return 15;
   }
   switch(curChar)
   {
      case 45:
         return jjMoveStringLiteralDfa16_1(active0, 0x400L);
      case 97:
         return jjMoveStringLiteralDfa16_1(active0, 0x200L);
      case 109:
         return jjMoveStringLiteralDfa16_1(active0, 0x800L);
      case 110:
         return jjMoveStringLiteralDfa16_1(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_1(14, active0);
}
private int jjMoveStringLiteralDfa16_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(14, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(15, active0);
      return 16;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa17_1(active0, 0x100L);
      case 101:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_1(16, 11, 0);
         break;
      case 109:
         return jjMoveStringLiteralDfa17_1(active0, 0x200L);
      case 110:
         return jjMoveStringLiteralDfa17_1(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_1(15, active0);
}
private int jjMoveStringLiteralDfa17_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(15, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(16, active0);
      return 17;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa18_1(active0, 0x400L);
      case 101:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_1(17, 9, 0);
         break;
      case 109:
         return jjMoveStringLiteralDfa18_1(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_1(16, active0);
}
private int jjMoveStringLiteralDfa18_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(16, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(17, active0);
      return 18;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_1(18, 8, 0);
         break;
      case 109:
         return jjMoveStringLiteralDfa19_1(active0, 0x400L);
      default :
         break;
   }
   return jjStartNfa_1(17, active0);
}
private int jjMoveStringLiteralDfa19_1(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(17, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(18, active0);
      return 19;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_1(19, 10, 0);
         break;
      default :
         break;
   }
   return jjStartNfa_1(18, active0);
}
private int jjStartNfaWithStates_1(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_1(state, pos + 1);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 1;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xdfffbfefffffffffL & l) == 0L)
                     break;
                  kind = 20;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xdfffffffd7ffffffL & l) == 0L)
                     break;
                  kind = 20;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjstateSet[jjnewStateCnt++] = 0;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 1 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa1_0(0x6L);
      case 69:
         return jjMoveStringLiteralDfa1_0(0x8L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 84:
         return jjMoveStringLiteralDfa2_0(active0, 0x6L);
      case 88:
         return jjMoveStringLiteralDfa2_0(active0, 0x8L);
      default :
         return 2;
   }
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 2;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 2;
   }
   switch(curChar)
   {
      case 80:
         if ((active0 & 0x8L) != 0L)
            return jjStopAtPos(2, 3);
         break;
      case 84:
         return jjMoveStringLiteralDfa3_0(active0, 0x6L);
      default :
         return 3;
   }
   return 3;
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 3;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 3;
   }
   switch(curChar)
   {
      case 82:
         return jjMoveStringLiteralDfa4_0(active0, 0x6L);
      default :
         return 4;
   }
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 4;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 4;
   }
   switch(curChar)
   {
      case 95:
         return jjMoveStringLiteralDfa5_0(active0, 0x6L);
      default :
         return 5;
   }
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 5;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 5;
   }
   switch(curChar)
   {
      case 76:
         return jjMoveStringLiteralDfa6_0(active0, 0x4L);
      case 81:
         return jjMoveStringLiteralDfa6_0(active0, 0x2L);
      default :
         return 6;
   }
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 6;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 6;
   }
   switch(curChar)
   {
      case 73:
         return jjMoveStringLiteralDfa7_0(active0, 0x4L);
      case 85:
         return jjMoveStringLiteralDfa7_0(active0, 0x2L);
      default :
         return 7;
   }
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 7;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 7;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa8_0(active0, 0x2L);
      case 78:
         return jjMoveStringLiteralDfa8_0(active0, 0x4L);
      default :
         return 8;
   }
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 8;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 8;
   }
   switch(curChar)
   {
      case 75:
         if ((active0 & 0x4L) != 0L)
            return jjStopAtPos(8, 2);
         break;
      case 76:
         return jjMoveStringLiteralDfa9_0(active0, 0x2L);
      default :
         return 9;
   }
   return 9;
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 9;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 9;
   }
   switch(curChar)
   {
      case 73:
         return jjMoveStringLiteralDfa10_0(active0, 0x2L);
      default :
         return 10;
   }
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 10;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 10;
   }
   switch(curChar)
   {
      case 70:
         return jjMoveStringLiteralDfa11_0(active0, 0x2L);
      default :
         return 11;
   }
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 11;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 11;
   }
   switch(curChar)
   {
      case 73:
         return jjMoveStringLiteralDfa12_0(active0, 0x2L);
      default :
         return 12;
   }
}
private int jjMoveStringLiteralDfa12_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 12;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 12;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa13_0(active0, 0x2L);
      default :
         return 13;
   }
}
private int jjMoveStringLiteralDfa13_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return 13;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 13;
   }
   switch(curChar)
   {
      case 68:
         if ((active0 & 0x2L) != 0L)
            return jjStopAtPos(13, 1);
         break;
      default :
         return 14;
   }
   return 14;
}
static final int[] jjnextStates = {
   7, 8, 9, 2, 3, 4, 5, 10, 11, 12, 13, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\101\124\124\122\137\121\125\101\114\111\106\111\105\104", 
"\101\124\124\122\137\114\111\116\113", "\105\130\120", "\56", "\175", "\43\160\141\162\145\156\164", 
"\43\160\141\162\145\156\164\55\151\144", "\43\160\141\162\145\156\164\55\165\156\151\161\165\145\55\156\141\155\145", 
"\43\160\141\162\145\156\164\55\163\150\157\162\164\55\156\141\155\145", 
"\43\160\141\162\145\156\164\55\144\151\163\160\154\141\171\55\156\141\155\145", "\43\160\141\162\145\156\164\55\164\171\160\145\55\156\141\155\145", 
"\43\151\144", "\43\161\165\141\154\151\146\151\145\144\55\156\141\155\145", 
"\43\156\141\155\145", "\43\165\156\151\161\165\145\55\156\141\155\145", 
"\43\163\150\157\162\164\55\156\141\155\145", "\43\144\151\163\160\154\141\171\55\156\141\155\145", 
"\43\164\171\160\145\55\156\141\155\145", "\43\154\151\156\153\55\164\171\160\145\55\156\141\155\145", null, "\133", 
null, "\135", "\75", "\44\173", null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "S2",
   "ATR",
   "OPT",
   "DEFAULT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, 3, -1, 
   1, -1, -1, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[14];
private final int[] jjstateSet = new int[28];
protected char curChar;
/** Constructor. */
public ExpressionParseTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ExpressionParseTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 14; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 4 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 3;
int defaultLexState = 3;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       break;
     case 2:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       break;
     case 3:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_3();
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
