package duke.command.logic;

import duke.util.CcaList;
import duke.util.JsonWrapper;
import duke.util.PlannerUi;
import duke.util.Storage;

import duke.modules.data.ModuleInfoDetailed;
import duke.util.commons.ModuleTasksList;
import java.util.HashMap;

public class ClearCommand extends ModuleCommand {

    public ClearCommand(Arguments args) {
        super(args);
    }

    @Override
    public void execute(HashMap<String, ModuleInfoDetailed> detailedMap,
                        ModuleTasksList tasks,
                        CcaList ccas,
                        PlannerUi plannerUi,
                        Storage store,
                        JsonWrapper jsonWrapper) {
        String toClear = arg("toClear");
        plannerUi.clearMsg(toClear);
        boolean confirm = plannerUi.confirm();
        if (confirm) {
            switch (toClear) {
                case ("modules"): {
                    tasks.clear();
                    break;
                }

                case ("ccas"): {
                    ccas.clear();
                    break;
                }

                // TODO: Add clear data capability
                // case ("data"): {
                // break;
                // }

                default: {
                    break;
                }
            }
            plannerUi.clearedMsg(toClear);
            jsonWrapper.storeTaskListAsJson(tasks.getTasks(), store);
        } else {
            plannerUi.abortMsg();
        }
    }
}
