export const menuData = [
  {
    name: '主页',
    icon: 'home',
    path: 'home',
  },
  {
    name: '系统设置',
    icon: 'setting',
    path: 'system',
    children: [
      {
        name: '用户设置',
        path: 'user',
      },
      {
        name: '角色设置',
        path: 'role',
      },
      {
        name: '编码类型',
        path: 'codeType',
      },
      {
        name: '编码规则',
        path: 'code',
      },
    ],
  },
  {
    name: '员工管理',
    icon: 'home',
    path: 'staff',
    children: [
      {
        name: '合同协议管理',
        path: 'contract',
      },
      {
        name: '公司档案信息',
        path: 'companyRecord',
      },
      {
        name: '能力档案',
        path: 'record',
        children: [
          {
            name: '计算机能力',
            path: 'it',
          },
          {
            name: '语言能力',
            path: 'lang',
          },
          {
            name: '专业技术',
            path: 'profession',
          },
        ],
      },
      {
        name: '奖惩制度',
        path: 'awardAndPunishment',
        children: [
          {
            name: '奖励信息',
            path: 'award',
          },
          {
            name: '惩处信息',
            path: 'punishment',
          },
        ],
      },
      {
        name: '其他信息',
        path: 'otherInfo',
        children: [
          {
            name: '残疾信息',
            path: 'disability',
          },
          {
            name: '工伤信息',
            path: 'occInjury',
          },
        ],
      },
      {
        name: '在华外籍员工',
        path: 'foreign',
        children: [
          {
            name: '工作许可证',
            path: 'visa',
          },
          {
            name: '居留签注证',
            path: 'reside',
          },
        ],
      },
    ],
  },
  {
    name: '组织管理',
    icon: 'fork',
    path: 'org',
  },
  {
    name: '人事总务管理',
    icon: 'home',
    path: 'hr',
    children: [
      {
        name: '员工基本信息',
        path: 'staff',
      },
      {
        name: '固定工资设置',
        path: 'eltSalary',
      },
    ],
  },
];
