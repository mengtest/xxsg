package com.linlongyx.sanguo.webgame.common.fight;

import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
import com.linlongyx.sanguo.webgame.proto.binary.fight.FightRecordResponse;
import com.linlongyx.sanguo.webgame.proto.binary.struct.BoutPlayData;
import java.util.List;

public interface IFight {
  public static final byte SIDE_TYPE_PLAYER = 0;
  
  public static final byte SIDE_TYPE_MONSTER = 1;
  
  IFightSide getSide(byte paramByte);
  
  List<IFighter> getCurFighters(byte paramByte, boolean paramBoolean);
  
  List<IFighter> getCurFightersExclude(byte paramByte1, boolean paramBoolean, byte paramByte2);
  
  void initSide(int paramInt, IFightSide paramIFightSide);
  
  BoutPlayData genBoutPlayData();
  
  void getRecord(FightRecordResponse paramFightRecordResponse);
  
  void appendDebugStr(String paramString);
  
  String getDebugStr();
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\IFight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */