import { AppPage } from './app.po';
// @ts-ignore
import { browser, logging } from 'protractor';

// @ts-ignore
describe('workspace-project App', () => {
  let page: AppPage;

  // @ts-ignore
    beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Welcome to project!');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
