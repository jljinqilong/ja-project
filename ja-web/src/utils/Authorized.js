import RenderAuthorized from '../components/Authorized';
import { getToken } from './authority'; // 取到currentAuthority

let Authorized = RenderAuthorized(getToken()); // eslint-disable-line

// Reload the rights component
const reloadAuthorized = () => {
  Authorized = RenderAuthorized(getToken());
};
export { reloadAuthorized };
export default Authorized;
