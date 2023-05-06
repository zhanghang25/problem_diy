




const readline = require('readline');
const fs = require('fs')

function getUserInput() {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });

  return new Promise(resolve => {
    rl.question('请输入数字范围和组合长度（以空格分隔）：', input => {
      const [n, k] = input.split(' ').map(Number);
      rl.question('请输入筛选条件（以空格分隔，不输入则不筛选）：', filter => {
        rl.close();
        resolve({ n, k, filter });
      });
    });
  });
}



function generateCombinations(n, k) {
    const combinations = [];
    const stack = [];
    let i = 0;
  
    while (true) {
      if (stack.length === k) {
        combinations.push([...stack]);
      }
  
      if (stack.length === k || i >= n) {
        if (stack.length === 0) {
          break;
        }
        i = stack.pop() + 1;
      } else {
        stack.push(i);
        i++;
      }
    }
  
    return combinations;
  }

  function filterCombinations(combinations, filter) {
    if (!filter) {
      return combinations;
    }
  
    const filteredCombinations = [...combinations];
  
    for (let i = filteredCombinations.length - 1; i >= 0; i--) {
      let shouldRemove = false;
      for (let j = 0; j < filter.length; j++) {
        if (!filteredCombinations[i].some(num => num.toString().includes(filter[j]))) {
          shouldRemove = true;
          break;
        }
      }
      if (shouldRemove) {
        filteredCombinations.splice(i, 1);
      }
    }
  
    return filteredCombinations;
  }

  function outputCombinations(combinations, outputFile) {
    if (combinations.length === 0) {
      fs.writeFileSync(outputFile, '没有符合条件的组合。');
      return;
    }
  
    const output = `共找到 ${combinations.length} 个符合条件的组合：
  ${combinations.map(combination => combination.join(' ')).join('\n')}`;
    fs.writeFileSync(outputFile, output);
  }

async function main() {
    let { n, k, filter } = await getUserInput();
    let combinations = generateCombinations(n, k);
    let outputToFile = true;
  
    while (true) {
      // 获取用户输入
      const { filter: newFilter } = await getUserInput();
  
      // 筛选组合
      combinations = filterCombinations(combinations, newFilter);
  
      const filteredCombinations = [...combinations];
  
      // 输出结果
      console.log(`剩余组合数：${filteredCombinations.length}`);
      if (outputToFile) {
        outputCombinations(filteredCombinations, 'output.txt');
        console.log('结果已输出到文件 output.txt');
      } else {
        console.log(filteredCombinations);
      }
  
      // 询问是否继续输出到文件
      const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
      });
      const answer = await new Promise(resolve => {
        rl.question('是否将结果输出到文件？（y/n）', resolve);
      });
      rl.close();
  
      outputToFile = answer.toLowerCase() === 'y';
  
      // 询问是否使用上一次过滤的结果作为下一次过滤的总组合数输入
      const useFiltered = await new Promise(resolve => {
        rl.question('是否使用上一次过滤的结果作为下一次过滤的总组合数输入？（y/n）', resolve);
      });
  
      if (useFiltered.toLowerCase() === 'y') {
        combinations = filteredCombinations;
      } else {
        // 获取新的数字范围和组合长度
        const { n: newN, k: newK } = await getUserInput();
        combinations = generateCombinations(newN, newK);
      }
    }
  }
  
  main();