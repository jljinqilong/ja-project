import React from 'react';
import EditableContext from './EditableContext';

export default class EditableRow extends React.Component {
  render() {
    const { form } = this.props;

    return (
      <EditableContext.Provider value={form}>
        <tr {...this.props} />
      </EditableContext.Provider>
    );
  }
}
