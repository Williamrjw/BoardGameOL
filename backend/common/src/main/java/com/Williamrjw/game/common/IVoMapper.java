package com.Williamrjw.game.common;

import java.util.List;

/**
 * vo转do范型
 * @param <VO> controller视图
 * @param <DOMAIN> 业务类型
 */
public interface IVoMapper<VO,DOMAIN> {
    VO do2vo(DOMAIN domain);

    List<VO> dos2vos(List<DOMAIN> domains);

    DOMAIN vo2do(VO vo);

    List<DOMAIN> vos2dos(List<VO> vos);
}