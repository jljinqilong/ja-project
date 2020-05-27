import { parse } from 'url';

const areaNameList = ['亚洲大区', '欧洲大区', '非洲大区'];
const countryNameList = [
  ['中国', '日本', '韩国'],
  ['德国', '意大利', '英国'],
  ['南非', '埃及', '印度'],
];

const stateNameListForSelect = [
  { rowId: 1, value: '亚洲' },
  { rowId: 2, value: '欧洲' },
  { rowId: 3, value: '非洲' },
];
const countryNameListForSelect = [
  { rowId: 1, value: '中国' },
  { rowId: 2, value: '日本' },
  { rowId: 3, value: '韩国' },
  { rowId: 4, value: '德国' },
  { rowId: 5, value: '意大利' },
  { rowId: 6, value: '英国' },
  { rowId: 7, value: '南非' },
  { rowId: 8, value: '埃及' },
  { rowId: 9, value: '印度' },
];
const currencyNameListForSelect = [
  { rowId: 1, value: '人民币' },
  { rowId: 2, value: '美元' },
  { rowId: 3, value: '日元' },
];

const count = { value: 1 };

const tableListDataSource = [];
for (let i = 1; i < 22; i += 1) {
  const index = i % 3;
  tableListDataSource.push({
    rowId: getNextSeq(),
    areaName: areaNameList[index],
    countryName: countryNameList[index].join(','),
    details: [
      {
        state: stateNameListForSelect[index].rowId,
        country: countryNameListForSelect[index].rowId,
        mainCurrency: 1,
        secondCurrency: 1,
      },
      {
        state: stateNameListForSelect[index].rowId,
        country: countryNameListForSelect[index].rowId,
        mainCurrency: 2,
        secondCurrency: 2,
      },
      {
        state: stateNameListForSelect[index].rowId,
        country: countryNameListForSelect[index].rowId,
        mainCurrency: 3,
        secondCurrency: 3,
      },
      {
        state: stateNameListForSelect[index].rowId,
        country: countryNameListForSelect[index].rowId,
        mainCurrency: 1,
        secondCurrency: 1,
      },
    ],
  });
}

function getNextSeq() {
  const { value } = count;
  count.value = value + 1;
  return value;
}

export function listArea(req, res, u) {
  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  const params = parse(url, true).query; // 解析查询参数

  let dataSource = [...tableListDataSource];

  // area name filter
  if (params.areaName) {
    dataSource = [...dataSource].filter(data => data.areaName.indexOf(params.areaName) !== -1);
  }

  // country name filter
  if (params.countryName) {
    dataSource = [...dataSource].filter(
      data => data.countryName.indexOf(params.countryName) !== -1
    );
  }

  // 页面大小
  let pageSize = 10;
  if (params.pageSize) {
    pageSize = params.pageSize * 1; // * 1 转换为整型
  }

  // 当前页
  const current = parseInt(params.pageNum, 10) || 1;

  // 数据总数
  const total = dataSource.length;

  // 数组分片，模拟后端分页
  dataSource = dataSource.slice((current - 1) * pageSize, current * pageSize);

  const result = {
    list: dataSource,
    pagination: {
      total,
      pageSize,
      current,
    },
  };

  if (res && res.json) {
    res.json(result);
  } else {
    return result;
  }
}

export function getArea(req, res, u) {
  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  let rowId = 0;
  const lastSplashIndex = url.lastIndexOf('/');
  if (lastSplashIndex > 0) {
    rowId = parseInt(url.substring(lastSplashIndex + 1), 10);
  }

  let dataSource = [...tableListDataSource];

  // area name filter
  dataSource = [...dataSource].filter(data => data.rowId === rowId);

  const result = dataSource.length > 0 ? dataSource[0] : {};

  if (res && res.json) {
    res.json(result);
  } else {
    return result;
  }
}

export function addArea(req, res, u, b) {
  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  const body = (b && b.body) || req.body;
  // const { method, no, description } = body;

  const item = {
    rowId: getNextSeq(),
    areaName: body.areaName,
    countryName: '手动增加',
    details: body.details,
  };

  // 模拟入库
  tableListDataSource.push(item);

  // 模拟结果
  const result = {
    msg: 'success',
    code: 200,
  };

  if (res && res.json) {
    res.json(result);
  } else {
    return result;
  }
}

export function delArea(req, res, u) {
  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  let rowId = 0;
  const lastSplashIndex = url.lastIndexOf('/');
  if (lastSplashIndex > 0) {
    rowId = parseInt(url.substring(lastSplashIndex + 1), 10);
  }

  // 模拟删除
  const index = tableListDataSource.findIndex(item => rowId === item.rowId);
  tableListDataSource.splice(index, 1);

  // 模拟结果
  const result = {
    msg: 'success',
    code: 200,
  };

  if (res && res.json) {
    res.json(result);
  } else {
    return result;
  }
}

export function editArea(req, res, u, b) {
  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  const body = (b && b.body) || req.body;

  // 模拟更新
  const index = tableListDataSource.findIndex(item => body.rowId === item.rowId);
  tableListDataSource.splice(index, 1, { ...body });

  // 模拟结果
  const result = {
    msg: 'success',
    code: 200,
  };

  if (res && res.json) {
    res.json(result);
  } else {
    return result;
  }
}

export function listState(req, res) {
  if (res && res.json) {
    res.json(stateNameListForSelect);
  } else {
    return stateNameListForSelect;
  }
}

export function listCountry(req, res) {
  if (res && res.json) {
    res.json(countryNameListForSelect);
  } else {
    return countryNameListForSelect;
  }
}

export function listCurrency(req, res) {
  if (res && res.json) {
    res.json(currencyNameListForSelect);
  } else {
    return currencyNameListForSelect;
  }
}
