import { createElement } from 'react';
import dynamic from 'dva/dynamic';
// import pathToRegexp from 'path-to-regexp';

let routerConfigCache;

const modelNotExisted = (app, model) => {
  // eslint-disable-next-line
  return !app._models.some(({ namespace }) => {
    return namespace === model.substring(model.lastIndexOf('/') + 1);
  });
};

export const getRouterConfig = app => {
  const routerConfig = {
    '/': {
      component: dynamicWrapper(app, ['user', 'login', 'systemUser'], () =>
        import('../layouts/BasicLayout')
      ),
    },
    '/home': {
      component: dynamicWrapper(app, [], () => import('../routes/Home/Home')),
    },
    '/home/my': {
      component: dynamicWrapper(app, [], () => import('../routes/Home/My/My')),
    },
    '/system': {
      component: dynamicWrapper(app, [], () => import('../routes/System/SystemHome')),
    },
    '/system/codeType': {
      component: dynamicWrapper(app, [], () => import('../routes/System/CodeType')),
    },
    '/system/code': {
      component: dynamicWrapper(app, [], () => import('../routes/System/Code')),
    },
    '/system/user': {
      component: dynamicWrapper(app, [], () => import('../routes/System/User')),
    },
    '/system/role': {
      component: dynamicWrapper(app, [], () => import('../routes/System/Role')),
    },
    '/system/workerCodeRule': {
      component: dynamicWrapper(app, [], () => import('../routes/System/WorkerCodeRule')),
    },
    '/hr': {
      component: dynamicWrapper(app, [], () => import('../routes/Hr/HrHome')),
    },
    '/org/org': {
      component: dynamicWrapper(app, [], () => import('../routes/Org/Org')),
    },
    '/org/position': {
      component: dynamicWrapper(app, ['position'], () => import('../routes/Org/Position')),
    },
    '/org/grade': {
      component: dynamicWrapper(app, ['grade'], () => import('../routes/Org/Grade')),
    },
    '/org/rank': {
      component: dynamicWrapper(app, ['rank'], () => import('../routes/Org/Rank')),
    },
    '/org/positiontype': {
      component: dynamicWrapper(app, ['positionType'], () => import('../routes/Org/PositionType')),
    },
    '/staff/baseInfo': {
      component: dynamicWrapper(app, ['staffBaseInfo'], () =>
        import('../routes/Staff/baseInfo/StaffBaseInfo')
      ),
    },
    '/staff/contract': {
      component: dynamicWrapper(app, [], () => import('../routes/Staff/contract/ContractTabs')),
    },
    '/staff/transaction': {
      component: dynamicWrapper(app, [], () => import('../routes/Staff/AdjustWork/AdjustWork')),
    },
    '/staff/transactionQuery': {
      component: dynamicWrapper(app, [], () => import('../routes/Staff/AdjustWork/TransactionQuery')),
    },
    '/staff/ModalDetailForm/:rowId/:type': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Staff/AdjustWork/ModalDetailForm')
      ),
    },
    '/emolument/LookStaff/:rowId': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/LookStaff')),
    },
    '/staff/ModelTabs/:rowId/:staffNo': {
      component: dynamicWrapper(app, [], () => import('../routes/Staff/baseInfo/ModelTabs')),
    },
    '/emolument/eltAccumulationFund': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/AccumulationFund')),
    },
    '/emolument/eltSocialSecurity': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/SocialSecurity')),
    },
    '/emolument/eltSalary': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/Salary')),
    },
    '/emolument/eltSubsidy': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/Subsidy')),
    },
    '/emolument/eltAllowance': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/Allowance')),
    },
    '/emolument/achievementsImport': {
      component: dynamicWrapper(app, [], () => import('../routes/Emolument/Performance')),
    },
    '/sale': {
      component: dynamicWrapper(app, [], () => import('../routes/Sale/Home/SaleHome')),
    },
    '/sale/area': {
      component: dynamicWrapper(app, ['area'], () => import('../routes/Sale/Area/Area')),
    },
    '/sale/customer': {
      component: dynamicWrapper(app, ['customer'], () =>
        import('../routes/Sale/Customer/Customer')
      ),
    },
    '/sale/customer/detail/:id': {
      component: dynamicWrapper(app, [], () => import('../routes/Sale/Customer/CustomerTabs')),
    },
    '/sale/saleContract': {
      component: dynamicWrapper(app, ['saleContract'], () =>
        import('../routes/Sale/SaleContract/SaleContract')
      ),
    },
    '/sale/saleContract/detail/:id': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Sale/SaleContract/SaleContractTabs')
      ),
    },
    '/sale/enquiry': {
      component: dynamicWrapper(app, ['enquiry'], () => import('../routes/Sale/Enquiry/Enquiry')),
    },
    '/sale/enquiry/detail/:id': {
      component: dynamicWrapper(app, [], () => import('../routes/Sale/Enquiry/EnquiryDetailTabs')),
    },
    '/sale/inquiries': {
      component: dynamicWrapper(app, ['inquiries'], () =>
        import('../routes/Sale/Enquiry/Inquiries')
      ),
    },
    '/sale/inquiriesAppraisal': {
      component: dynamicWrapper(app, ['inquiriesAppraisal'], () =>
        import('../routes/Sale/Enquiry/InquiriesAppraisal')
      ),
    },
    '/sale/inquiriesAppraisal/detail/:id': {
      component: dynamicWrapper(app, ['inquiriesAppraisal'], () =>
        import('../routes/Sale/Enquiry/InquiriesAppraisalDetailTabs')
      ),
    },
    '/sale/returnMoney': {
      component: dynamicWrapper(app, ['returnMoney'], () =>
        import('../routes/Sale/ReturnMoney/ReturnMoney')
      ),
    },
    '/sale/returnMoney/paymentPlan/detail/:id': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Sale/ReturnMoney/PaymentPlanTabs')
      ),
    },
    '/sale/returnMoney/payment/detail/:id': {
      component: dynamicWrapper(app, [], () => import('../routes/Sale/ReturnMoney/PaymentTabs')),
    },
    '/sale/stockUp': {
      component: dynamicWrapper(app, ['stockUp'], () => import('../routes/Sale/StockUp/StockUp')),
    },
    '/sale/stockUp/detail/:id': {
      component: dynamicWrapper(app, [], () => import('../routes/Sale/StockUp/StockUpTabs')),
    },

    '/sale/product': {
      component: dynamicWrapper(app, ['product'], () => import('../routes/Sale/Product/Product')),
    },
    '/sale/product/detail/:id': {
      component: dynamicWrapper(app, [], () => import('../routes/Sale/Product/ProductTabs')),
    },
    '/attendance/attendanceMachine': {
      component: dynamicWrapper(app, [], () => import('../routes/Attendance/Machine/Machine')),
    },
    '/attendance/scheduling': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/Scheduling/Scheduling')
      ),
    },
    '/attendance/specialShift': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/SpecialShift/SpecialShift')
      ),
    },
    '/attendance/creditCardLog': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/CreditCardLog/CreditCardLog')
      ),
    },
    '/attendance/exemptions': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/Exemptions/Exemptions')
      ),
    },
    '/attendance/holidayType': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/HolidayType/HolidayType')
      ),
    },
    '/attendance/overtimeSheet': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/OvertimeSheet/OvertimeSheet')
      ),
    },
    '/attendance/personalLeave': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/PersonalLeave/PersonalLeave')
      ),
    },
    '/attendance/sellOff': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/SellOff/SellOffTabs')
      ),
    },
    '/attendance/businessTravel': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/BusinessTravel/BusinessTravel')
      ),
    },
    '/attendance/record': {
      component: dynamicWrapper(app, [], () =>
        import('../routes/Attendance/Record/Record')
      ),
    },
    '/exception/403': {
      component: dynamicWrapper(app, [], () => import('../routes/Exception/403')),
    },
    '/exception/404': {
      component: dynamicWrapper(app, [], () => import('../routes/Exception/404')),
    },
    '/exception/500': {
      component: dynamicWrapper(app, [], () => import('../routes/Exception/500')),
    },
    '/user': {
      component: dynamicWrapper(app, [], () => import('../layouts/UserLayout')),
    },
    '/user/login': {
      identity: 'common',
      component: dynamicWrapper(app, ['login'], () => import('../routes/User/Login')),
    },
  };
  const { _store } = app;
  _store.dispatch({
    type: 'global/saveRouterConfig',
    payload: routerConfig,
  });
  return routerConfig;
};
// wrapper of dynamic
const dynamicWrapper = (app, models, component) => {
  // () => require('module')
  // transformed by babel-plugin-dynamic-import-node-sync
  if (component.toString().indexOf('.then(') < 0) {
    models.forEach(model => {
      if (modelNotExisted(app, model)) {
        // eslint-disable-next-line
        app.model(require(`../models/${model}`).default);
      }
    });

    return props => {
      if (!routerConfigCache) {
        routerConfigCache = getRouterConfig(app);
      }
      return createElement(component().default, {
        ...props,
        routerConfig: routerConfigCache,
      });
    };
  }
  // () => import('module')
  return dynamic({
    app,
    models: () =>
      models.filter(model => modelNotExisted(app, model)).map(m => import(`../models/${m}`)),
    // add routerData prop
    component: () => {
      if (!routerConfigCache) {
        routerConfigCache = getRouterConfig(app);
      }
      return component().then(raw => {
        const Component = raw.default || raw;
        return props =>
          createElement(Component, {
            ...props,
            routerConfig: routerConfigCache,
          });
      });
    },
  });
};
