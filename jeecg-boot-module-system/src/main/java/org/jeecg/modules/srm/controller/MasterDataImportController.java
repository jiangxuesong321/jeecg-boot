package org.jeecg.modules.srm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.srm.entity.BasMaterialCategory;
import org.jeecg.modules.srm.entity.ExcelModel;
import org.jeecg.modules.srm.mapper.BasMaterialCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/srm/dataImport")
@Slf4j
public class MasterDataImportController {

    @Autowired
    private BasMaterialCategoryMapper basMaterialCategoryMapper;

    @ApiOperation(value = "物料主数据导入Excel", notes = "返回导入情况接口")
    @PostMapping(value = "/material")
    public Result<String> excelProTbZbzs(@RequestParam("file") MultipartFile file) {
        try {
            File file1 = new File("C:\\jiangxuesong\\1111111111洛钼SRM\\物料主数据推导关系66633.xlsx");
            FileInputStream fis = new FileInputStream(file1);
            Workbook workBook = WorkbookFactory.create(fis);
            workBook.getSheet("1");
            int numberOfSheets = workBook.getNumberOfSheets(); //获取有几个sheet
            List<Object> list = new ArrayList<>();


            Sheet sheetAt = workBook.getSheetAt(0);
            int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();

            List<ExcelModel> excelList = new ArrayList<>();
            List<ExcelModel> setList = new ArrayList<>();
            for (int i = 1; i < rowsOfSheet; i++) {
                Row row0 = sheetAt.getRow(i);
                ExcelModel excelModel = new ExcelModel();
                System.out.println("第几行" + i);
                if (row0 != null) {

                    if ("STRING".equals(row0.getCell(0).getCellType() + "")) {
                        excelModel.setCell00(row0.getCell(0).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(0).getNumericCellValue() + "");
                        excelModel.setCell00(aa + "");
                    }
                    if ("STRING".equals(row0.getCell(1).getCellType() + "")) {
                        excelModel.setCell01(row0.getCell(1).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(1).getNumericCellValue() + "");
                        excelModel.setCell01(aa + "");
                    }

                    if ("STRING".equals(row0.getCell(2).getCellType() + "")) {
                        excelModel.setCell02(row0.getCell(2).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(2).getNumericCellValue() + "");
                        excelModel.setCell02(aa + "");
                    }

                    if ("STRING".equals(row0.getCell(3).getCellType() + "")) {
                        excelModel.setCell03(row0.getCell(3).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(3).getNumericCellValue() + "");
                        excelModel.setCell03(aa + "");
                    }

                    if ("STRING".equals(row0.getCell(4).getCellType() + "")) {
                        excelModel.setCell04(row0.getCell(4).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(4).getNumericCellValue() + "");
                        excelModel.setCell04(aa + "");
                    }

                    if ("STRING".equals(row0.getCell(5).getCellType() + "")) {
                        excelModel.setCell05(row0.getCell(5).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(5).getNumericCellValue() + "");
                        excelModel.setCell05(aa + "");
                    }

                    System.out.println(row0.getCell(6).getCellType());
                    System.out.println(row0.getCell(6));
                    System.out.println(excelModel.getCell05());
                    if ("STRING".equals(row0.getCell(6).getCellType() + "")) {
                        excelModel.setCell06(row0.getCell(6).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(6).getNumericCellValue() + "");
                        excelModel.setCell06(aa + "");
                    }

                    if ("STRING".equals(row0.getCell(7).getCellType() + "")) {
                        excelModel.setCell07(row0.getCell(7).getStringCellValue());
                    } else {
                        int aa = (int) Float.parseFloat(row0.getCell(7).getNumericCellValue() + "");
                        excelModel.setCell07(aa + "");
                    }
                    excelList.add(excelModel);
                }
            }
            setList = excelList;
            setList = setList.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ExcelModel::getCell00))), ArrayList::new));

            List<ExcelModel> dealList = new ArrayList<>();
            outer:
            for (int i = 0; i < setList.size(); i++) {
                String cell00 = setList.get(i).getCell00();
                dealList = excelList.stream().filter(s -> s.getCell00().equals(cell00)).collect(Collectors.toList());

                for (int j = 0; j < dealList.size(); j++) {
                    String cell000 = dealList.get(j).getCell00();
                    String cell001 = dealList.get(j).getCell01();
                    LambdaQueryWrapper<BasMaterialCategory> query = new LambdaQueryWrapper<>();
                    query.eq(BasMaterialCategory::getCode, cell000);
                    BasMaterialCategory oneGet = basMaterialCategoryMapper.selectOne(query);
                    if (oneGet == null || oneGet.getId() == null) {
                        BasMaterialCategory model = new BasMaterialCategory();
                        model.setPid("0");
                        model.setHasChild("0");
                        model.setCode(cell000);
                        model.setName(cell001);
                        model.setIsEnabled(1);
                        model.setCreateBy("admin");
                        model.setCreateTime(new Date());
                        model.setVersion(0);
                        basMaterialCategoryMapper.insert(model);
                    }
                    oneGet = basMaterialCategoryMapper.selectOne(query);
                    //第3和第4列
                    String cell002 = dealList.get(j).getCell02();
                    String cell003 = dealList.get(j).getCell03();
                    LambdaQueryWrapper<BasMaterialCategory> query1 = new LambdaQueryWrapper<>();
                    query1.eq(BasMaterialCategory::getCode, cell002);
                    BasMaterialCategory oneGet1 = basMaterialCategoryMapper.selectOne(query1);
                    if (oneGet1 == null || oneGet1.getId() == null) {
                        BasMaterialCategory model1 = new BasMaterialCategory();
                        model1.setPid(oneGet.getId());
                        model1.setHasChild("0");
                        model1.setCode(cell002);
                        model1.setName(cell003);
                        model1.setIsEnabled(1);
                        model1.setCreateBy("admin");
                        model1.setCreateTime(new Date());
                        model1.setVersion(1);
                        basMaterialCategoryMapper.insert(model1);
                        oneGet.setHasChild("1");
                        oneGet.setUpdateBy("admin");
                        oneGet.setUpdateTime(new Date());
                        basMaterialCategoryMapper.updateById(oneGet);
                    }

                    oneGet1 = basMaterialCategoryMapper.selectOne(query1);
                    //第5和第6列
                    String cell004 = dealList.get(j).getCell04();
                    String cell005 = dealList.get(j).getCell05();
                    LambdaQueryWrapper<BasMaterialCategory> query2 = new LambdaQueryWrapper<>();
                    query2.eq(BasMaterialCategory::getCode, cell004);
                    BasMaterialCategory oneGet2 = basMaterialCategoryMapper.selectOne(query2);
                    if (oneGet2 == null || oneGet2.getId() == null) {
                        BasMaterialCategory model2 = new BasMaterialCategory();
                        model2.setPid(oneGet1.getId());
                        model2.setHasChild("0");
                        model2.setCode(cell004);
                        model2.setName(cell005);
                        model2.setIsEnabled(1);
                        model2.setCreateBy("admin");
                        model2.setCreateTime(new Date());
                        model2.setVersion(2);
                        basMaterialCategoryMapper.insert(model2);
                        oneGet1.setHasChild("1");
                        oneGet1.setUpdateBy("admin");
                        oneGet1.setUpdateTime(new Date());
                        basMaterialCategoryMapper.updateById(oneGet1);
                    }

                    oneGet2 = basMaterialCategoryMapper.selectOne(query2);
                    //第7和第8列
                    String cell006 = dealList.get(j).getCell06();
                    String cell007 = dealList.get(j).getCell07();
                    LambdaQueryWrapper<BasMaterialCategory> query3 = new LambdaQueryWrapper<>();
                    query3.eq(BasMaterialCategory::getCode, cell006);
                    BasMaterialCategory oneGet3 = basMaterialCategoryMapper.selectOne(query3);
                    if (oneGet3 == null || oneGet3.getId() == null) {
                        BasMaterialCategory model3 = new BasMaterialCategory();
                        model3.setPid(oneGet2.getId());
                        model3.setHasChild("0");
                        model3.setCode(cell006);
                        model3.setName(cell007);
                        model3.setIsEnabled(1);
                        model3.setCreateBy("admin");
                        model3.setCreateTime(new Date());
                        model3.setVersion(3);
                        basMaterialCategoryMapper.insert(model3);
                        oneGet2.setHasChild("1");
                        oneGet2.setUpdateBy("admin");
                        oneGet2.setUpdateTime(new Date());
                        basMaterialCategoryMapper.updateById(oneGet2);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.OK("导入成功！");
    }
}
