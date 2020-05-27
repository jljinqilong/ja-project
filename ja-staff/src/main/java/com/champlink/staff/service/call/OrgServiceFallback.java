package com.champlink.staff.service.call;

import org.springframework.stereotype.Component;

@Component
public class OrgServiceFallback implements OrgService {

    @Override
    public String getAllOrg() {
        return null;
    }

    @Override
    public String baseList() {
        return null;
    }

    @Override
    public String deptList() {
        return null;
    }

    @Override
    public String findOnePositon(Long id) {
        return null;
    }

    @Override
    public String findOneGrade(Long id) {
        return null;
    }

    @Override
    public String findOneRank(Long id) {
        return null;
    }

    @Override
    public String getAllPosition() {
        return null;
    }

    @Override
    public String getAllGrade() {
        return null;
    }

    @Override
    public String getAllRank() {
        return null;
    }

    @Override
    public String getAllSonOrg(Long baseId) {
        return null;
    }

    @Override
    public String getAllGradeByPosition(Long positionId) {
        return null;
    }

    @Override
    public String getAllRankByPositionAndGrade(Long positionId, Long gradeId) {
        return null;
    }

    @Override
    public String get(Long id) {
        return null;
    }

}
