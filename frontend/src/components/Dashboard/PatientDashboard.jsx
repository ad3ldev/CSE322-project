const PatientDashboard = ({ patient }) => {
	let isFemale = patient.gender === "F" ? true : false;
	return (
		<>
			{isFemale && <h1>Hello Mrs. {patient.name}</h1>}
			{!isFemale && <h1>Hello Mr. {patient.name}</h1>}
		</>
	);
};

export default PatientDashboard;
