package linlongyx.sanguo.webgame.common.fight.fighter;

import linlongyx.sanguo.webgame.common.fight.IFight;
import linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
import com.linlongyx.sanguo.webgame.proto.binary.struct.FighterData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
import java.util.List;
import java.util.Map;

public interface IFighter {
  void initGuid(byte paramByte);
  
  long getId();
  
  void setId(long paramLong);
  
  byte getSide();
  
  void setSide(byte paramByte);
  
  byte getGuid();
  
  byte getPos();
  
  byte getCalcPos();
  
  void setPos(byte paramByte);
  
  Byte getFury();
  
  byte decFury(byte paramByte);
  
  byte addFury(byte paramByte, boolean paramBoolean);
  
  Byte getAtomFury();
  
  void setAtomFury(byte paramByte);
  
  byte getMaxFury();
  
  long getHP();
  
  long decHP(long paramLong, IFighter paramIFighter);
  
  long addHP(long paramLong);
  
  long getMaxHp();
  
  long getHPPer();
  
  FighterData getFighterData();
  
  boolean isAuto();
  
  void preStart(IFight paramIFight);
  
  boolean canFight();
  
  byte getType();
  
  byte getHurtType();
  
  int auto(IFight paramIFight);
  
  void replace(IFight paramIFight, IFighter paramIFighter);
  
  long getDefAttr();
  
  long getCalcAttr(AttributeType paramAttributeType);
  
  long getCalcAttr(AttributeType paramAttributeType, boolean paramBoolean);
  
  long getAttr(int paramInt);
  
  void setAttr(int paramInt, long paramLong);
  
  long getBaseAttr(int paramInt);
  
  void setBaseAttr(int paramInt, long paramLong);
  
  boolean addBuff(IFight paramIFight, int paramInt, IFighter paramIFighter);
  
  boolean addBuff(IFight paramIFight, int paramInt, long paramLong, IFighter paramIFighter, ResultPlayData paramResultPlayData);
  
  List<AbstractBuff> getBuffList();
  
  void addKillNum();
  
  int getKillNum();
  
  boolean removeBuff(ResultPlayData paramResultPlayData);
  
  boolean removeBuff(ResultPlayData paramResultPlayData, int paramInt1, int paramInt2, int paramInt3);
  
  void clearBuff();
  
  boolean hasBuff(int paramInt);
  
  boolean isDead();
  
  boolean isDisorder();
  
  boolean isForbidAction();
  
  long getPid();
  
  boolean setDead(boolean paramBoolean);
  
  short getLevel();
  
  Map<Integer, Integer> getTalisman();
  
  void setTalisman(Map<Integer, Integer> paramMap);
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\IFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */