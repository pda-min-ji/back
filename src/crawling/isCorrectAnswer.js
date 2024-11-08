import puppeteer from 'puppeteer-extra';
import StealthPlugin from 'puppeteer-extra-plugin-stealth';

puppeteer.use(StealthPlugin());

const [,, questionNumber, bojId] = process.argv;
export const isCorrectAnswer = async (QuestionNumber, BOJId) => {
  const browser = await puppeteer.launch({
    headless: true,
    args: [
      '--no-sandbox',
      '--disable-setuid-sandbox',
      '--disable-dev-shm-usage',
      '--disable-accelerated-2d-canvas',
      '--disable-gpu',
    ],
  });
  
  const page = await browser.newPage();

  // User-Agent 설정
  await page.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36");

  // 페이지 이동
  const url = `https://www.acmicpc.net/status?problem_id=${QuestionNumber}&user_id=${BOJId}&language_id=-1&result_id=4`;
  await page.goto(url, { waitUntil: 'domcontentloaded', timeout: 10000 });

  // 페이지의 HTML 가져오기
  const html = await page.content();
  await browser.close();

  // 정규식으로 solution_ids 배열 부분 추출
  const solutionIdsMatch = html.match(/var solution_ids = (\[.*?\]);/);
  if (solutionIdsMatch && solutionIdsMatch[1]) {
    // 마지막 쉼표를 제거하여 JSON으로 파싱
    const cleanedSolutionIds = solutionIdsMatch[1].replace(/,\s*]$/, ']');
    const solutionIds = JSON.parse(cleanedSolutionIds);


    // solutionIds가 비었는지 확인
    if (solutionIds.length > 0) {
      console.log(true)
    } else {
      console.log(false)
    }
  } else {
    console.log(false)
  }

};

// 실행
(async () => {
  await isCorrectAnswer(questionNumber, bojId);
})();
